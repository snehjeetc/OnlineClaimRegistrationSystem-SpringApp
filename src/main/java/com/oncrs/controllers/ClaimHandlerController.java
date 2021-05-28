package com.oncrs.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oncrs.dtos.ClaimDataDTO;
import com.oncrs.dtos.ReportDTO;
import com.oncrs.dtos.ResponseDTO;

@RestController
@RequestMapping("/oncrs/claims/")
public class ClaimHandlerController {
	
	@GetMapping("/generateReport")
	public ResponseEntity<ResponseDTO> generateReport(@RequestBody ReportDTO reportDto){
		return new ResponseEntity<>(new ResponseDTO(null, ""), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createClaim(@RequestBody ClaimDataDTO claimDataDto){
		return new ResponseEntity<>(new ResponseDTO(null, ""), HttpStatus.ACCEPTED);
	}
}
