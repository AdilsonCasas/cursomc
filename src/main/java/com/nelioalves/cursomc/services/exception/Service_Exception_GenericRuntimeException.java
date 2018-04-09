package com.nelioalves.cursomc.services.exception;

public class Service_Exception_GenericRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public Service_Exception_GenericRuntimeException(String msg) {
		super(msg);
	}

	public Service_Exception_GenericRuntimeException(String msg, Throwable cause) {
		super(msg,cause);
	}

}
