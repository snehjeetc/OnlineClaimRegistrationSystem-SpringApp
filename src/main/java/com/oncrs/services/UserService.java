package com.oncrs.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oncrs.auth.ApplicationUser;
import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.dtos.PolicyDataDTO;
import com.oncrs.dtos.ReturnValue;
import com.oncrs.dtos.UserInfoDTO;
import com.oncrs.exception.PolicyException;
import com.oncrs.exception.PolicyException.PolicyExceptionType;
import com.oncrs.exception.UserException;
import com.oncrs.exception.UserException.UserExceptionType;
import com.oncrs.models.PolicyData;
import com.oncrs.models.UserInfo;
import com.oncrs.repositories.IUserInfoRepository;
import com.oncrs.security.ApplicationUserRole;


@Service
public class UserService implements IUserService{
	
	@Autowired
	private IPolicyDataService policyDataService;
	
	@Autowired
	private IUserInfoRepository userRepo;
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public List<UserInfo> getUsers(){
		return this.userRepo.findAll();
	}

	@Override
	public UserInfoDTO registerUser(UserInfo newUser) {
		
		if(newUser.getUserId().toUpperCase().equals("ADMIN"))
			throw new UserException(UserExceptionType.USER_ALREADY_EXISTS,
									"UserId can't be admin");
	
		List<UserInfo> existingUser = this.userRepo.findByUserId(newUser.getUserId());
		
		if(existingUser.size()==0) {
			newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
			UserInfo userGen = this.userRepo.save(newUser);
			return new UserInfoDTO(
						userGen.getUserId(),
						userGen.getRole(),
						userGen.getPolicies());
		}
		else 
			throw new UserException(UserExceptionType.USER_ALREADY_EXISTS,
									"User with " + newUser.getUserId() + " already exists");
	}
	
	@Override
	public Optional<ApplicationUser> loadUserByUserName(String username){
		
		UserInfo user = this.currentUser(username);
		return Optional.of(new ApplicationUser(
				user.getUserId(),
				user.getPassword(),
				this.getAuthority(user.getRole()),
				true,
				true,
				true,
				true
				));
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
	public ReturnValue getPolicies(String userid) {
		
		UserInfo currentUser = this.currentUser(userid);
		return new ReturnValue(
				currentUser.getUserId(), 
				currentUser.getRole(), 
				currentUser.getPolicies());
	}

	@Override
	public ReturnValue getClaims(String userid) {
		UserInfo currentUser = this.currentUser(userid);
		List<PolicyData> claimedPolicies = currentUser.getPolicies()
													.stream()
													.filter(policy -> policy.getClaimPolicy() != null)
													.collect(Collectors.toList());
		return new ReturnValue(
				currentUser.getUserId(), 
				currentUser.getRole(), 
				claimedPolicies);
	}

	@Override
	public UserInfoDTO createPolicy(PolicyDataDTO policy) {	
		UserInfo currentUser = this.currentUser(policy.getUserId());
		currentUser.addPolicyData(new PolicyData( 
									0l,
									policy.getAccountNumber(),
									policy.getPremiumAmount(),
									null));
		
		UserInfo updateUser = this.userRepo.save(currentUser);
		return new UserInfoDTO(updateUser.getUserId(),
							  updateUser.getRole(),
							  updateUser.getPolicies());
	}

	@Override
	public UserInfoDTO createClaim(ClaimDataDTO claim) {
		UserInfo user = this.currentUser(claim.getUserId());
		this.policyDataService.claimPolicy(claim.getPolicyNumber(), claim);
		user = this.currentUser(claim.getUserId());
		return new UserInfoDTO(user.getUserId(),
				  			   user.getRole(),
				  			   user.getPolicies());
	}

	@Override
	public ReturnValue getClaim(String userId, Long claimNumber) {
		
		UserInfo currentUser = this.currentUser(userId);
		PolicyData policyData = currentUser.getPolicies()
											.stream()
											.filter(policy ->{ 
												if(policy.getClaimPolicy() != null &&
												policy.getClaimPolicy().getClaimNo().equals(claimNumber))
													return true;
												return false;
											})
											.findFirst()
											.orElseThrow(()->new PolicyException(
													PolicyExceptionType.CLAIM_NOT_FOUND,
													"Claim with " + claimNumber + " not found"));
		return new ReturnValue(currentUser.getUserId(),
							   currentUser.getRole(),
							   policyData);
	}
	
	@Override
	public UserInfoDTO generateReport(String userId) {
		UserInfo currentUser = this.currentUser(userId);
		return new UserInfoDTO(currentUser.getUserId(),
							   currentUser.getRole(),
							   currentUser.getPolicies());
	}
	
	private UserInfo currentUser(String currentUserId) {
		List<UserInfo> user = this.userRepo.findByUserId(currentUserId);
		if( user.size()==0) {
			throw new UserException(UserExceptionType.USER_NOT_FOUND,
					"User with " + currentUserId + " not found");						
		}
		return user.get(0);
	}
	
	
}
