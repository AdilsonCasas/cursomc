package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoEntityPK Id = new ItemPedidoEntityPK();

	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedidoEntity() {
	}

	public ItemPedidoEntity(PedidoEntity var_pedido, ProdutoEntity var_produto, Double var_desconto, Integer var_quantidade, Double var_preco) {
		super();
		this.Id.setPedido(var_pedido);
		this.Id.setProduto(var_produto);
		this.desconto = var_desconto;
		this.quantidade = var_quantidade;
		this.preco = var_preco;
	}

	// o método abaixo não foi gerado automáricamente, mas implementado manualmente, e ao colocar "get" no início do nome do método ele é reconhecido e serializado
	// para o Json automaticamente, ou seja, em uma consulta tipo 'GET pedido' no postman, o Json coloca "SubTotal" em cada itemdepedido
	public double getSubTotal() {
		return (this.preco - this.desconto) * this.quantidade;
	}

	@JsonIgnore
	public PedidoEntity getPedido() {
		return this.Id.getPedido();
	}
	
	public void setPedido(PedidoEntity var_pedido) {
		this.Id.setPedido(var_pedido);
	}
	
	//@JsonIgnore : o produto é desejável que seja mostrado então não vamoss ignorá-lo
	public ProdutoEntity getProduto() {
		return this.Id.getProduto();
	}
	
	public void setProduto(ProdutoEntity var_produto) {
		this.Id.setProduto(var_produto);
	}
	
	@JsonIgnore
	public ItemPedidoEntityPK getId() {
		return this.Id;
	}

	public void setId(ItemPedidoEntityPK var_id) {
		this.Id = var_id;
	}

	public Double getDesconto() {
		return this.desconto;
	}

	public void setDesconto(Double var_desconto) {
		this.desconto = var_desconto;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Integer var_quantidade) {
		this.quantidade = var_quantidade;
	}

	public Double getPreco() {
		return this.preco;
	}

	public void setPreco(Double var_preco) {
		this.preco = var_preco;
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
		ItemPedidoEntity other = (ItemPedidoEntity) obj;
		if (this.Id == null) {
			if (other.Id != null)
				return false;
		} else if (!this.Id.equals(other.Id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat var_numeroFormatado = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		StringBuilder var_builder = new StringBuilder();
		var_builder.append(getProduto().getNome());
		var_builder.append(", Qtde: ");
		var_builder.append(getQuantidade());
		var_builder.append(", Preco unitário: ");
		var_builder.append(var_numeroFormatado.format(getPreco()));
		var_builder.append(", Subtotal: ");
		var_builder.append(var_numeroFormatado.format(getPreco()));
		var_builder.append("\n");
		return var_builder.toString();
	}

}
