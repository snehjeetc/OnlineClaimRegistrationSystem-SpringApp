package com.oncrs.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.oncrs.dtos.ResponseDTO;

@RestController
@RequestMapping("/oncrs/login")
public class LoginController {
	
	@GetMapping("/welcome")
	public ResponseEntity<ResponseDTO> login(){
		return new ResponseEntity<>(new ResponseDTO("Welcome to ONCRS", "Login Success"),
								HttpStatus.ACCEPTED);
	}
}
