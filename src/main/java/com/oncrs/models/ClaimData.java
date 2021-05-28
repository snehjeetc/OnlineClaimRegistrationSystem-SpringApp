package com.oncrs.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimData {
	
	private Long claimNo;
	
	private String claimReason;
	private String accidentLocationStreet;
	private String city;
	private String state;
	private Integer zip;
	private String claimType;	
	
}
