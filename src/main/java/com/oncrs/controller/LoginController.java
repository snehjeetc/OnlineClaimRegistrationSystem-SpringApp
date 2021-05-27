package com.oncrs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oncrs.dto.LoginDetailsDTO;
import com.oncrs.dto.ResponseDTO;
import com.oncrs.model.UserDetails;

@RestController
@RequestMapping("/oncrs/login")
public class LoginController {
	
	@PostMapping
	public ResponseEntity<ResponseDTO> login(@RequestBody UserDetails userDetails){
		return new ResponseEntity<>(new ResponseDTO(new LoginDetailsDTO(userDetails.getUserId(), null), "Login Success"),
								HttpStatus.ACCEPTED);
	}
}
