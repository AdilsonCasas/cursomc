package com.nelioalves.cursomc.services.exceptions;

public class service_exceptionObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public service_exceptionObjectNotFoundException(String msg) {
		super(msg);
	}

	public service_exceptionObjectNotFoundException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
