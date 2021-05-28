package com.oncrs.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
	INSURED,
	CLAIMHANDLER,
	CLAIMADJUSTER;
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		 Set<SimpleGrantedAuthority> permission = new HashSet<>();
		 permission.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		 return permission;
	}
}
