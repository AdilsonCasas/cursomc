package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import com.nelioalves.cursomc.domain.domainCategoria;

public class DTO_Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	private String  nome;
	
	public DTO_Categoria() {
	}
	
	public DTO_Categoria(domainCategoria obj) {
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
