package com.nelioalves.cursomc.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.domain.ClienteDomain;
import com.nelioalves.cursomc.services.validation.Service_Annotation_ClienteUpdate;

@Service_Annotation_ClienteUpdate
public class DTO_ClienteDomain_nome_email implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	
	// as diretivas "@NotEmpty" e "@Length" abaixo é parte do "Bean Validate" que faz parte do Java EE, o Bean Validate define o tipo de validação com estas diretivas e envia a "message" de retorno ao REST
	@NotEmpty(message="Preenchimento do Nome do Cliente é obrigatório.")
	@Length(min=5, max=120, message="O tamanho do Nome do Cliente deve estar entre 5 e 120 caracteres.")
	private String nome;
	
	@NotEmpty(message="Preenchimento do Email do Cliente é obrigatório.")
	@Email(message="Email inválido.")
	private String email;
	
	public DTO_ClienteDomain_nome_email() {
	}
	
	public DTO_ClienteDomain_nome_email(ClienteDomain obj) {
		this.Id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer id) {
		this.Id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}