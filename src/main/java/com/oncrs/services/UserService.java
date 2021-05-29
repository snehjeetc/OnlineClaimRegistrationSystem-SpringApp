package com.oncrs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oncrs.auth.ApplicationUser;
import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.dtos.PolicyDataDTO;
import com.oncrs.dtos.UserInfoDTO;
import com.oncrs.models.ClaimData;
import com.oncrs.models.PolicyData;
import com.oncrs.models.UserInfo;
import com.oncrs.security.ApplicationUserRole;


@Service
public class UserService implements IUserService{
	
	@Autowired
	private IPolicyDataService policyDataService;
	
	@Autowired
	private IClaimDataService claimDataService;
	
	private List<UserInfo> userRepo;
	private static AtomicLong autoGenerateToken;
	
	private final PasswordEncoder passwordEncoder;
	
	static {
		autoGenerateToken = new AtomicLong();
	}
	
	@Autowired
	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.userRepo = new ArrayList<>();
		this.userRepo.add(new UserInfo(autoGenerateToken.getAndIncrement(), "admin", this.passwordEncoder.encode("admin"), "CLAIMADJUSTER", null));
	}
	
	@Override
	public List<UserInfo> getUsers(){
		return this.userRepo;
	}

	@Override
	public UserInfoDTO registerUser(UserInfo newUser) {
		UserInfoDTO userGen;
		if(this.isValidUser(newUser)) {
			newUser.setUserNo(autoGenerateToken.getAndIncrement());
			String password = newUser.getPassword();
			newUser.setPassword(passwordEncoder.encode(password));
			this.userRepo.add(newUser);
			userGen = new UserInfoDTO(newUser.getUserId(), newUser.getRole(), null);
		}
		else {
			userGen = null;
		}
		return userGen;
	}

	private Boolean isValidUser(UserInfo newUser) {
		UserInfo userExisting = this.userRepo.stream()
										.filter(user -> {
											return user.getUserId().equals(newUser.getUserId())
													&& user.getPassword().equals(newUser.getPassword());
										})
										.findFirst()
										.orElse(null);
		if(userExisting == null)
			return true;
		return false;
	}
	
	@Override
	public Optional<ApplicationUser> loadUserByUserName(String username){
		
		return this.userRepo.stream()
									.filter(user -> username.equals(user.getUserId()))
									.map(user -> new ApplicationUser(
											user.getUserId(),
											user.getPassword(),
											this.getAuthority(user.getRole()),
											true,
											true,
											true,
											true
											)
										)
									.findFirst();
	}
	
	private Set<? extends GrantedAuthority> getAuthority(String userRole){
		if(userRole.equals(ApplicationUserRole.INSURED.name())) {
			return ApplicationUserRole.INSURED.getGrantedAuthorities();
		}
		else if(userRole.equals(ApplicationUserRole.CLAIMHANDLER.name())) {
			return ApplicationUserRole.CLAIMHANDLER.getGrantedAuthorities();
		}
		else
			return ApplicationUserRole.CLAIMADJUSTER.getGrantedAuthorities();
	}

	@Override
	public List<PolicyData> getPolicies(String userid) {
		
		UserInfo currentUser = this.currentUser(userid);
		if(currentUser == null)
			return null;
		return currentUser.getPolicies();
	}

	@Override
	public List<ClaimData> getClaims(String userid) {
		
		List<PolicyData> policiesOfUser = getPolicies(userid);
		if(policiesOfUser == null)
			return null;
		
		List<ClaimData> userClaims = policiesOfUser
											.stream()
											.filter(policy -> policy.getClaimPolicy() != null)
											.map(policy -> policy.getClaimPolicy())
											.collect(Collectors.toList());
		return userClaims;
	}

	@Override
	public UserInfoDTO createPolicy(PolicyDataDTO policy) {
		
		UserInfo currentUser = this.currentUser(policy.getUserId());
		if(currentUser == null)
			return new UserInfoDTO();
		PolicyData createdPolicy = this.policyDataService.addPolicyData(policy, currentUser.getUserNo());
		currentUser.addPolicyData(createdPolicy);
		return new UserInfoDTO(currentUser.getUserId(), 
							   currentUser.getRole(), 
							   currentUser.getPolicies());
	}

	@Override
	public UserInfoDTO createClaim(ClaimDataDTO claim) {
		
		UserInfo currentUser = this.currentUser(claim.getUserId());
		if(currentUser == null)
			return new UserInfoDTO();
		
		PolicyData claimedPolicy = currentUser.getPolicies()
												.stream()
												.filter(policy -> 
														claim.getPolicyNumber()
																.equals(policy.getPolicyNumber()))
												.findFirst()
												.orElse(null);
		if(claimedPolicy == null)
			return new UserInfoDTO();
		
		ClaimData claimed = this.policyDataService.claimPolicy(claim);
		return new UserInfoDTO(currentUser.getUserId(), 
							   currentUser.getRole(), 
							   currentUser.getPolicies());
	}

	@Override
	public ClaimData getClaim(String userId, Long claimNumber) {
		
		UserInfo currentUser = this.currentUser(userId);
		if(currentUser == null)
			return new ClaimData();
		
		return this.claimDataService.getClaim(claimNumber);
	}
	
	@Override
	public UserInfoDTO generateReport(String userId) {
		UserInfo currentUser = this.currentUser(userId);
		if(currentUser==null)
			return null;
		return new UserInfoDTO(currentUser.getUserId(),
							   currentUser.getRole(),
							   currentUser.getPolicies());
	}
	
	private UserInfo currentUser(String currentUserId) {
		return this.userRepo
				.stream()
				.filter(user -> currentUserId.equals(user.getUserId()))
				.findFirst()
				.orElse(null);
	}
	
	
}
