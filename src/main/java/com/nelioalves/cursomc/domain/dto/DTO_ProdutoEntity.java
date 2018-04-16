package com.nelioalves.cursomc.domain.dto;

import java.io.Serializable;

import com.nelioalves.cursomc.domain.ProdutoEntity;

public class DTO_ProdutoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer Id;
	private String nome;
	private Double preco;
	
	public DTO_ProdutoEntity() {
	}

	public DTO_ProdutoEntity(ProdutoEntity var_obj) {
		this.Id = var_obj.getId();
		this.nome = var_obj.getNome();
		this.preco = var_obj.getPreco();
		
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer var_id) {
		Id = var_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String var_nome) {
		this.nome = var_nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double var_preco) {
		this.preco = var_preco;
	}

}
