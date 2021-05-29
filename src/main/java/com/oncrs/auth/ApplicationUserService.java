package com.oncrs.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oncrs.security.ApplicationUserRole;
import com.oncrs.services.IUserService;

@Service
public class ApplicationUserService implements UserDetailsService{

	@Autowired
	private IUserService userService;
	
	private final PasswordEncoder passwordEncoder;
	private final ApplicationUser adminUser;
	
	@Autowired
	public ApplicationUserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.adminUser = new ApplicationUser(
											"admin",
											this.passwordEncoder.encode("admin"),
											ApplicationUserRole.CLAIMADJUSTER.getGrantedAuthorities(),
											true,
											true,
											true,
											true);
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equals(this.adminUser.getUsername()))
			return this.adminUser;
		else
			return this.userService.loadUserByUserName(username)
					.orElseThrow(()->
						new UsernameNotFoundException(String.format("Userid % not found ", 
															username)));
	}
	
}
