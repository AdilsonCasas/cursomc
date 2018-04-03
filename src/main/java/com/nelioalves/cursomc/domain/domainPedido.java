package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
public class domainPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
	private domainPagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private domainCliente cliente;
	
	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	private domainEndereco enderecoDeEntrega;
	
	@OneToMany(mappedBy="Id.pedido")
	private Set<domainItemPedido> itens = new HashSet<>();

	public domainPedido() {
	}

	public domainPedido(Integer id, Date instante, domainCliente cliente, domainEndereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return this.instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public domainPagamento getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(domainPagamento pagamento) {
		this.pagamento = pagamento;
	}

	public domainCliente getCliente() {
		return this.cliente;
	}

	public void setCliente(domainCliente cliente) {
		this.cliente = cliente;
	}

	public domainEndereco getEnderecoDeEntrega() {
		return this.enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(domainEndereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Set<domainItemPedido> getItens() {
		return this.itens;
	}

	public void setItens(Set<domainItemPedido> itens) {
		this.itens = itens;
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
		domainPedido other = (domainPedido) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
