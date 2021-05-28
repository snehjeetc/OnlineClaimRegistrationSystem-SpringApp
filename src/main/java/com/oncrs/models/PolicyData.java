package com.oncrs.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyData {
	
	private Long policyNumber;
	
	private Long accountNumber;
	private double premiumAmount;
	private Long userNo;
	
	
	private ClaimData claimPolicy;
}
