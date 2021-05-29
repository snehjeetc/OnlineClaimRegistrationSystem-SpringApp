package com.oncrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.dtos.ReportDTO;
import com.oncrs.dtos.ResponseDTO;
import com.oncrs.services.IPolicyDataService;
import com.oncrs.services.IUserService;

@RestController
@RequestMapping("/oncrs/claims/")
public class ClaimHandlerController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IPolicyDataService policyDataService;
	
	@GetMapping("/allPoliciesAndClaims")
	@PreAuthorize("hasRole('ROLE_CLAIMADJUSTER')")
	public ResponseEntity<ResponseDTO> fetchAllClaims(){
		return new ResponseEntity<>(new ResponseDTO(
											this.policyDataService.getAllPolicies(),
											"All policies and claims fetched"),
											HttpStatus.OK);
	}
	
	@GetMapping("/generateClaimReport")
	@PreAuthorize("hasAnyRole('ROLE_CLAIMADJUSTER', 'ROLE_CLAIMHANDLER')")
	public ResponseEntity<ResponseDTO> generateReport(@RequestBody ReportDTO reportDto){
		return new ResponseEntity<>(new ResponseDTO(
											this.userService.getClaim(reportDto.getUserId(), reportDto.getClaimNo()), 
											"Claim Report Generated"), 
											HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createClaim(@RequestBody ClaimDataDTO claimDataDto){
		return new ResponseEntity<>(new ResponseDTO(
											this.userService.createClaim(claimDataDto), 
											"Claim Created"), 
											HttpStatus.ACCEPTED);
	}
}
