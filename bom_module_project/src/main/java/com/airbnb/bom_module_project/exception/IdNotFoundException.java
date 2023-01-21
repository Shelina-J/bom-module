package com.airbnb.bom_module_project.exception;


public class IdNotFoundException extends RuntimeException {
	
	private String msg; 
	
	public IdNotFoundException(String msg) {
		super(msg);
		this.msg=msg;
	}
}
