package com.oncrs.exception;

@SuppressWarnings("serial")
public class PolicyException extends RuntimeException{
	
	public enum PolicyExceptionType{
		POLICY_NOT_FOUND,
		CLAIM_NOT_FOUND,
		POLICIES_NOT_FOUND
	}
	
	private final PolicyExceptionType exceptionType;
	
	public PolicyException(PolicyExceptionType exceptionType, String message) {
		super(message);
		this.exceptionType = exceptionType;
	}
	
	public PolicyExceptionType getExceptionType() {
		return this.exceptionType;
	}
	
}
