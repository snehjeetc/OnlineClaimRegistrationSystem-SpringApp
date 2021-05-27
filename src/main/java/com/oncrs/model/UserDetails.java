package com.oncrs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
	//Validation
	
	
	String userId; 	//login id
	String password;	
	Integer roleId;
}
