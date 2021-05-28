package com.oncrs.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oncrs.dtos.ResponseDTO;
import com.oncrs.models.UserInfo;
import com.oncrs.services.IUserService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	public IUserService  userService;
	
	@PostMapping("/user")
	public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserInfo user) {
		return new ResponseEntity<>(new ResponseDTO(
										this.userService.registerUser(user), "User Success"),
										HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/showusers")
	public ResponseEntity<ResponseDTO> showUsers(){
		return new ResponseEntity<>(new ResponseDTO(this.userService.getUsers(), "Fetched data"), HttpStatus.OK);
	}
}
