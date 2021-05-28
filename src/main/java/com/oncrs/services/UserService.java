package com.oncrs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oncrs.auth.ApplicationUser;
import com.oncrs.dtos.LoginDetailsDTO;
import com.oncrs.models.UserInfo;
import com.oncrs.security.ApplicationUserRole;


@Service
public class UserService implements IUserService{
	
	private List<UserInfo> userRepo;
	
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.userRepo = new ArrayList<>();
		this.userRepo.add(new UserInfo("admin", this.passwordEncoder.encode("admin"), "CLAIMADJUSTER"));
	}
	
	@Override
	public List<UserInfo> getUsers(){
		return this.userRepo;
	}

	@Override
	public UserInfo registerUser(UserInfo newUser) {
		UserInfo userGen = new UserInfo();
		if(this.isValidUser(newUser)) {
			String password = newUser.getPassword();
			newUser.setPassword(passwordEncoder.encode(password));
			this.userRepo.add(newUser);
			userGen.setUserId(newUser.getUserId());
			userGen.setRole(newUser.getRole());
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
	
}
