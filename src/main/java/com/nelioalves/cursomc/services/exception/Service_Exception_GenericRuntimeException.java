package com.nelioalves.cursomc.services.exception;

public class Service_Exception_GenericRuntimeException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public Service_Exception_GenericRuntimeException(String var_msg) {
		super(var_msg);
	}

	public Service_Exception_GenericRuntimeException(String var_msg, Throwable var_cause) {
		super(var_msg, var_cause);
	}

}
