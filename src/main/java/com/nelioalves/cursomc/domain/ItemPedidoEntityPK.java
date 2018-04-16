package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemPedidoEntityPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private PedidoEntity pedido;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private ProdutoEntity produto;
	
	public PedidoEntity getPedido() {
		return this.pedido;
	}
	public void setPedido(PedidoEntity var_pedido) {
		this.pedido = var_pedido;
	}
	public ProdutoEntity getProduto() {
		return this.produto;
	}
	public void setProduto(ProdutoEntity var_produto) {
		this.produto = var_produto;
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
		ItemPedidoEntityPK other = (ItemPedidoEntityPK) obj;
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
