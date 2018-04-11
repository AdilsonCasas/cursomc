package com.nelioalves.cursomc.resources;

import java.io.Serializable;

public class REST_exceptionFieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	
	public REST_exceptionFieldMessage() {
	}

	public REST_exceptionFieldMessage(String var_fieldName, String var_message) {
		super();
		this.fieldName = var_fieldName;
		this.message = var_message;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String var_fieldName) {
		this.fieldName = var_fieldName;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String var_message) {
		this.message = var_message;
	}
	
	
	
}