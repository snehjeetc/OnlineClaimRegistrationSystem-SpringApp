package com.oncrs.exception;

@SuppressWarnings("serial")
public class UserException extends RuntimeException{
	
	public enum UserExceptionType{
		USER_ALREADY_EXISTS,
		USER_NOT_FOUND
	}
	
	private final UserExceptionType exceptionType;
	
	public UserException(UserExceptionType exceptionType, String message) {
		super(message);
		this.exceptionType = exceptionType;
	}
	
	public UserExceptionType getExceptionType() {
		return this.exceptionType;
	}
}
