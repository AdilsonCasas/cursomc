package com.nelioalves.cursomc.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.domain.CategoriaEntity;

public class DTO_CategoriaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	
	// as diretivas "@NotEmpty" e "@Length" abaixo é parte do "Bean Validate" que faz parte do Java EE, ele define a validação definida nestes parâmetros e coloca a "message" de retorno ao REST
	@NotEmpty(message="Preenchimento do Nome da Categoria é obrigatório.")
	@Length(min=5, max=80, message="O tamanho do Nome da Categoria deve estar entre 5 e 80 caracteres.")
	private String  nome;
	
	public DTO_CategoriaEntity() {
	}
	
	public DTO_CategoriaEntity(CategoriaEntity var_obj) {
		this.Id = var_obj.getId();
		this.nome = var_obj.getNome();
	}

	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer var_id) {
		this.Id = var_id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String var_nome) {
		this.nome = var_nome;
	}

}
