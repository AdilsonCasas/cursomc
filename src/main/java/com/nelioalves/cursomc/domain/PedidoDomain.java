package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PedidoDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private PagamentoDomain pagamento;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private ClienteDomain cliente;
	
	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	private EnderecoDomain enderecoDeEntrega;
	
	@OneToMany(mappedBy="Id.pedido")
	private Set<ItemPedidoDomain> itens = new HashSet<>();

	public PedidoDomain() {
	}

	public PedidoDomain(Integer var_id, Date var_instante, ClienteDomain var_cliente, EnderecoDomain var_enderecoDeEntrega) {
		super();
		this.id = var_id;
		this.instante = var_instante;
		this.cliente = var_cliente;
		this.enderecoDeEntrega = var_enderecoDeEntrega;
	}

	public double getValorTotal() {
		double soma = 0.0;
		for (ItemPedidoDomain ip: this.itens) {
			soma = soma + ip.getSubTotal();
		}
		return soma;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer var_id) {
		this.id = var_id;
	}

	public Date getInstante() {
		return this.instante;
	}

	public void setInstante(Date var_instante) {
		this.instante = var_instante;
	}

	public PagamentoDomain getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(PagamentoDomain var_pagamento) {
		this.pagamento = var_pagamento;
	}

	public ClienteDomain getCliente() {
		return this.cliente;
	}

	public void setCliente(ClienteDomain var_cliente) {
		this.cliente = var_cliente;
	}

	public EnderecoDomain getEnderecoDeEntrega() {
		return this.enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(EnderecoDomain var_enderecoDeEntrega) {
		this.enderecoDeEntrega = var_enderecoDeEntrega;
	}

	public Set<ItemPedidoDomain> getItens() {
		return this.itens;
	}

	public void setItens(Set<ItemPedidoDomain> var_itens) {
		this.itens = var_itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
		PedidoDomain other = (PedidoDomain) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat var_numeroFormatado = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		SimpleDateFormat var_simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder var_builder = new StringBuilder();
		var_builder.append("Pedido Número: ");
		var_builder.append(getId());
		var_builder.append(", Instante: ");
		var_builder.append(var_simpleDateFormat.format(getInstante()));
		var_builder.append(", Cliente: ");
		var_builder.append(getCliente().getNome());
		var_builder.append(", Situação do Pagamento: ");
		var_builder.append(getPagamento().getEstado().getDescricao());
		var_builder.append("\nDetalhes:\n");
		for(ItemPedidoDomain ip: getItens()) {
			var_builder.append(ip.toString());
		}
		var_builder.append("Valor Total: ");
		var_builder.append(var_numeroFormatado.format(getValorTotal()));
		return var_builder.toString();
	}
	
	
}
