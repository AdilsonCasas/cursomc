package com.nelioalves.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

public class REST_exceptionValidationError extends REST_exceptionStandardError {
	private static final long serialVersionUID = 1L;

	private List<REST_exceptionFieldMessage> errors = new ArrayList<>();
	
	public REST_exceptionValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<REST_exceptionFieldMessage> getErrors() {
		return errors;
	}

	public void REST_exceptionValidationError_addError(String fieldName, String message) {
		this.errors.add(new REST_exceptionFieldMessage(fieldName, message));
	}
	
}
