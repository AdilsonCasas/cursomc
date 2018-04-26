package com.nelioalves.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class REST_exceptionValidationError extends REST_exceptionStandardError {

	private static final long serialVersionUID = 1L;

	private List<REST_exceptionFieldMessage> errors = new ArrayList<>();
	
	public REST_exceptionValidationError(Long var_timestamp, Integer var_status, String var_error, String var_message, String var_path) {
		super(var_timestamp, var_status, var_error, var_message, var_path);
	}
	
	public List<REST_exceptionFieldMessage> getErrors() {
		return this.errors;
	}

	public void REST_exceptionValidationError_addError(String var_fieldName, String var_message) {
		this.errors.add(new REST_exceptionFieldMessage(var_fieldName, var_message));
	}

}
