package com.nelioalves.cursomc.domain.dto;

import java.io.Serializable;

public class DTO_Credenciais implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	
	public DTO_Credenciais() {
	}
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String var_email) {
		this.email = var_email;
	}
	public String getSenha() {
		return this.senha;
	}
	public void setSenha(String var_senha) {
		this.senha = var_senha;
	}
}