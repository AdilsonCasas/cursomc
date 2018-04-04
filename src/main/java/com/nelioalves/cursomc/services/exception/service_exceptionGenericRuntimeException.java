package com.nelioalves.cursomc.services.exception;

public class service_exceptionGenericRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public service_exceptionGenericRuntimeException(String msg) {
		super(msg);
	}

	public service_exceptionGenericRuntimeException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
