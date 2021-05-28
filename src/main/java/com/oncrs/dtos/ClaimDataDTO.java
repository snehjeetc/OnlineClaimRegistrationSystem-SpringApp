package com.oncrs.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDataDTO {
	
	@NotBlank(message="User id mandatory")
	private String userId;
	
	@NotBlank(message = "Claim Reason mandatory")
	private String claimReason;
	
	@NotBlank(message="Accident Location Street is mandatory")
	private String accidentLocationStreet;
	
	@NotBlank(message = "City is a required field")
	private String city;
	
	@NotBlank(message="State is a required field")
	private String state;
	
	@NotNull
	@Min(value = 100001, message = "Invalid zip")
	@Max(value = 999999, message = "Invalid zip")
	private Integer zip;
	
	@NotBlank(message = "Claim Type cannot be empty")
	private String claimType;
	
	@NotNull
	private Long policyNumber;
}
