package com.nelioalves.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class REST_exceptionValidationError extends REST_exceptionStandardError {

	private static final long serialVersionUID = 1L;

	private List<REST_exceptionFieldMessage> var_errors = new ArrayList<>();
	
	public REST_exceptionValidationError(Integer var_status, String var_msg, String var_origem, Long var_timeStamp) {
		super(var_status, var_msg, var_origem, var_timeStamp);
	}

	public List<REST_exceptionFieldMessage> getErrors() {
		return this.var_errors;
	}

	public void REST_exceptionValidationError_addError(String var_fieldName, String var_message) {
		this.var_errors.add(new REST_exceptionFieldMessage(var_fieldName, var_message));
	}
	
}
