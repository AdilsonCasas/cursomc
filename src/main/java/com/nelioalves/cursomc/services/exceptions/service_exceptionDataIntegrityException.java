package com.nelioalves.cursomc.services.exceptions;

public class service_exceptionDataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public service_exceptionDataIntegrityException(String msg) {
		super(msg);
	}

	public service_exceptionDataIntegrityException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
