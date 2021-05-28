package com.oncrs.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oncrs.services.IUserService;

@Service
public class ApplicationUserService implements UserDetailsService{

	@Autowired
	private IUserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userService.loadUserByUserName(username)
				.orElseThrow(()->
				new UsernameNotFoundException(String.format("Userid % not found ", 
															username)));
	}
	
}
