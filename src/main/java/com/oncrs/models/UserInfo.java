package com.oncrs.models;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
	//Validation
	
	@Pattern(regexp = "[a-zA-Z0-9]{5,20}", 
			message = "Login id should be alpha numeric of [5, 20] chars")
	String userId; 	//login id
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
			message="Password Req upper case, lower case, numeric, and a special char of min length 6")
	String password;
	
	@Pattern(regexp="INSURED|CLAIMHANDLER|CLAIMADJUSTER")
	String role;
	

}
