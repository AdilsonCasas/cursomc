package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemPedidoDomainPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private PedidoDomain pedido;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private ProdutoDomain produto;
	
	public PedidoDomain getPedido() {
		return this.pedido;
	}
	public void setPedido(PedidoDomain pedido) {
		this.pedido = pedido;
	}
	public ProdutoDomain getProduto() {
		return this.produto;
	}
	public void setProduto(ProdutoDomain produto) {
		this.produto = produto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.pedido == null) ? 0 : this.pedido.hashCode());
		result = prime * result + ((this.produto == null) ? 0 : this.produto.hashCode());
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
		ItemPedidoDomainPK other = (ItemPedidoDomainPK) obj;
		if (this.pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!this.pedido.equals(other.pedido))
			return false;
		if (this.produto == null) {
			if (other.produto != null)
				return false;
		} else if (!this.produto.equals(other.produto))
			return false;
		return true;
	}

}
