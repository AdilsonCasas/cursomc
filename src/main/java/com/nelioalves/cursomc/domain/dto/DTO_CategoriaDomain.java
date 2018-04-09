package com.nelioalves.cursomc.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.domain.CategoriaDomain;

public class DTO_CategoriaDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	
	// as diretivas "@NotEmpty" e "@Length" abaixo é parte do "Bean Validate" que faz parte do Java EE, ele define a validação definida nestes parâmetros e coloca a "message" de retorno ao REST
	@NotEmpty(message="Preenchimento do Nome da Categoria é obrigatório.")
	@Length(min=5, max=80, message="O tamanho do Nome da Categoria deve estar entre 5 e 80 caracteres.")
	private String  nome;
	
	public DTO_CategoriaDomain() {
	}
	
	public DTO_CategoriaDomain(CategoriaDomain obj) {
		this.Id = obj.getId();
		this.nome = obj.getNome();
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

}
