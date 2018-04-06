package com.nelioalves.cursomc.domain.dto;

import java.io.Serializable;

import com.nelioalves.cursomc.domain.domainProduto;

public class domainDTO_Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer Id;
	private String nome;
	private Double preco;
	
	public domainDTO_Produto() {
	}

	public domainDTO_Produto(domainProduto obj) {
		this.Id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
		
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}