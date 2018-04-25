package com.nelioalves.cursomc.domain.dto;

import java.io.Serializable;

import com.nelioalves.cursomc.domain.EstadoEntity;

public class DTO_Estado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	private String  nome;
	
	public DTO_Estado() {
	}
	
	public DTO_Estado(EstadoEntity var_obj) {
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
