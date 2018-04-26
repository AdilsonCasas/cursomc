package com.nelioalves.cursomc.resources.exception;

import java.io.Serializable;

public class REST_exceptionStandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long    timestamp;
	private Integer status;
	private String  error;
	private String  message;
	private String  path;

	public REST_exceptionStandardError(Long var_timestamp, Integer var_status, String var_error, String var_message, String var_path) {
		super();
		this.timestamp = var_timestamp;
		this.status = var_status;
		this.error = var_error;
		this.message = var_message;
		this.path = var_path;
	}

	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long var_timestamp) {
		this.timestamp = var_timestamp;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer var_status) {
		this.status = var_status;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String var_error) {
		this.error = var_error;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String var_message) {
		this.message = var_message;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String var_path) {
		this.path = var_path;
	}

}
