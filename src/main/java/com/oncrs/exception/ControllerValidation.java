package com.oncrs.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oncrs.dtos.ResponseDTO;

@ControllerAdvice
public class ControllerValidation {
	
	private static final String message = "Error while processing request";
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception){
		List<String> errorMsgs = 
				exception.getAllErrors()
							.stream()
							.map(error -> error.getDefaultMessage())
							.collect(Collectors.toList());
		return new ResponseEntity<>(new ResponseDTO(errorMsgs, message), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ResponseDTO> handleUserException(UserException userException){
		return new ResponseEntity<>(new ResponseDTO(
										userException.getMessage(), 
										message),
										HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PolicyException.class)
	public ResponseEntity<ResponseDTO> handlePolicyException(PolicyException policyException){
		return new ResponseEntity<>(new ResponseDTO(
												policyException.getMessage(),
												message),
												HttpStatus.BAD_REQUEST);
	}
	
}
