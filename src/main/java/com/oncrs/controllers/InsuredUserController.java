package com.oncrs.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.dtos.ResponseDTO;

@RestController
@RequestMapping("/oncrs/user/")
public class InsuredUserController {
	
	@GetMapping("/viewPolicies")
	public ResponseEntity<ResponseDTO> getPolicies(@RequestParam String userId){
		return new ResponseEntity<>(new ResponseDTO(null, ""), HttpStatus.OK);
	}
	
	@GetMapping("/viewClaims")
	public ResponseEntity<ResponseDTO> getClaims(@RequestParam String userId){
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(null, ""), HttpStatus.OK);
	}
	
	@PostMapping("/claim")
	public ResponseEntity<ResponseDTO> createClaim(@Valid @RequestBody ClaimDataDTO claim){
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(null, ""), HttpStatus.ACCEPTED);
	}
	
	
}
