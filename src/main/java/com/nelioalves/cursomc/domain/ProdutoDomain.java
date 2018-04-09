package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProdutoDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	private String nome;
	private Double preco;

	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "PRODUTO_CATEGORIA",
			joinColumns = @JoinColumn(name = "produto_id"),
			inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<CategoriaDomain> categorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="Id.produto")
	private Set<ItemPedidoDomain> itens = new HashSet<>();
	
	public ProdutoDomain() {
	}

	public ProdutoDomain(Integer id, String nome, Double preco) {
		super();
		this.Id = id;
		this.nome = nome;
		this.preco = preco;
	}

	@JsonIgnore
	public List<PedidoDomain> getPedidos() {
		List<PedidoDomain> lista = new ArrayList<>();
		for(ItemPedidoDomain x: this.itens) {
			lista.add(x.getPedido());
		}
		return lista;
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

	public Double getPreco() {
		return this.preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<CategoriaDomain> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(List<CategoriaDomain> categorias) {
		this.categorias = categorias;
	}

	public Set<ItemPedidoDomain> getItens() {
		return this.itens;
	}

	public void setItens(Set<ItemPedidoDomain> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.Id == null) ? 0 : this.Id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoDomain other = (ProdutoDomain) obj;
		if (this.Id == null) {
			if (other.Id != null)
				return false;
		} else if (!this.Id.equals(other.Id))
			return false;
		return true;
	}	
}
