package com.anschau.udemy.services.exceptions;

public class AuthenticationException extends RuntimeException {

	
	//não funciona
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String msg) {
		super(msg);
	}
	
	public AuthenticationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
