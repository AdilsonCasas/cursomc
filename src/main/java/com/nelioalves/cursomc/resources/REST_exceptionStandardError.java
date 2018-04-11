package com.nelioalves.cursomc.resources;

import java.io.Serializable;

public class REST_exceptionStandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private Long timeStamp;
	
	public REST_exceptionStandardError(Integer var_status, String var_msg, Long var_timeStamp) {
		super();
		this.status = var_status;
		this.msg = var_msg;
		this.timeStamp = var_timeStamp;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer var_status) {
		this.status = var_status;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public void setMsg(String var_msg) {
		this.msg = var_msg;
	}
	
	public Long getTimeStamp() {
		return this.timeStamp;
	}
	
	public void setTimeStamp(Long var_timeStamp) {
		this.timeStamp = var_timeStamp;
	}
	
}
