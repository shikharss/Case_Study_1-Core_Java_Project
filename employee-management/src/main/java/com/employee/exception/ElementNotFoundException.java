package com.employee.exception;

public class ElementNotFoundException extends Exception{

	private String errorCode;
	
	public ElementNotFoundException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	@Override
	public String getMessage() {
		return this.errorCode+": "+super.getMessage();
	}
}