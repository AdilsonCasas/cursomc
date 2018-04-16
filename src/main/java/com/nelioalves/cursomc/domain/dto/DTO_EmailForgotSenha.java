package com.nelioalves.cursomc.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class DTO_EmailForgotSenha implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento do Email do Cliente é obrigatório.")
	@Email(message="Email inválido.")
	private String email;
	
	public DTO_EmailForgotSenha() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String var_email) {
		this.email = var_email;
	}

}
