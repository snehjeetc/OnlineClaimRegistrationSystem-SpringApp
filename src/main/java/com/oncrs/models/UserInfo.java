package com.oncrs.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info_table")
public class UserInfo {
	//Validation
	
	//Primary key
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_no")
	private Long userNo;
	
	@Pattern(regexp = "[a-zA-Z0-9]{5,20}", 
			message = "Login id should be alpha numeric of [5, 20] chars")
	@Column(name="user_id")
	private String userId; 	//login id
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
			message="Password Req upper case, lower case, numeric, and a special char of min length 6")
	private String password;
	
	@Pattern(regexp="INSURED|CLAIMHANDLER|CLAIMADJUSTER")
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<PolicyData> policies;
	
	public void addPolicyData(PolicyData policy) {
		if(this.policies == null)
			this.policies = new ArrayList<>();
		this.policies.add(policy);
	}
}
