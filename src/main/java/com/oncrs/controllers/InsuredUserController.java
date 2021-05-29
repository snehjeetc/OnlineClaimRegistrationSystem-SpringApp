package com.oncrs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oncrs.dtos.ResponseDTO;
import com.oncrs.services.IUserService;

@RestController
@RequestMapping("/oncrs/user/")
public class InsuredUserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/viewPolicies")
	public ResponseEntity<ResponseDTO> getPolicies(@RequestParam String userId){
		return new ResponseEntity<>(new ResponseDTO(
											this.userService.getPolicies(userId), 
											"Policies Fetched"), 
											HttpStatus.OK);
	}
	
	@GetMapping("/viewClaims")
	public ResponseEntity<ResponseDTO> getClaims(@RequestParam String userId){
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(
													this.userService.getClaims(userId), 
													"Claims Fetched"), 
													HttpStatus.OK);
	}
		
}
