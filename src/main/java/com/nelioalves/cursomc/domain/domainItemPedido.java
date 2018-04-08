package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class domainItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private domainItemPedidoPK Id = new domainItemPedidoPK();

	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public domainItemPedido() {
	}

	public domainItemPedido(domainPedido pedido, domainProduto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		this.Id.setPedido(pedido);
		this.Id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	// o método abaixo não foi gerado automáricamente, mas implementado manualmente, e ao colocar "get" no início do nome do método ele é reconhecido e serializado
	// para o Json automaticamente, ou seja, em uma consulta tipo 'GET pedido' no postman, o Json coloca "SubTotal" em cada itemdepedido
	public double getSubTotal() {
		return (preco - desconto) * quantidade;
	}

	@JsonIgnore
	public domainPedido getPedido() {
		return this.Id.getPedido();
	}
	
	public void setPedido(domainPedido pedido) {
		this.Id.setPedido(pedido);
	}
	
	//@JsonIgnore : o produto é desejável que seja mostrado então não vamoss ignorá-lo
	public domainProduto getProduto() {
		return this.Id.getProduto();
	}
	
	public void setProduto(domainProduto produto) {
		this.Id.setProduto(produto);
	}
	
	@JsonIgnore
	public domainItemPedidoPK getId() {
		return this.Id;
	}

	public void setId(domainItemPedidoPK id) {
		this.Id = id;
	}

	public Double getDesconto() {
		return this.desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return this.preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		domainItemPedido other = (domainItemPedido) obj;
		if (this.Id == null) {
			if (other.Id != null)
				return false;
		} else if (!this.Id.equals(other.Id))
			return false;
		return true;
	}

}
