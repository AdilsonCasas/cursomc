package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nelioalves.cursomc.domain.enums.enumEstadoPagamento;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
// Aula 48 - Inserindo Pedido: inserido '@JsonTypeInfo' para processar arquivo Json na inclusão de pedido
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class PagamentoDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer Id;
	private Integer estado;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private PedidoDomain pedido;
	
	public PagamentoDomain() {
	}

	public PagamentoDomain(Integer id, enumEstadoPagamento estado, PedidoDomain pedido) {
		super();
		this.Id = id;
		this.estado = (estado == null) ? null : estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer id) {
		this.Id = id;
	}

	public enumEstadoPagamento getEstado() {
		return enumEstadoPagamento.toEnum(this.estado);
	}

	public void setEstado(enumEstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public PedidoDomain getPedido() {
		return this.pedido;
	}

	public void setPedido(PedidoDomain pedido) {
		this.pedido = pedido;
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
		PagamentoDomain other = (PagamentoDomain) obj;
		if (this.Id == null) {
			if (other.Id != null)
				return false;
		} else if (!this.Id.equals(other.Id))
			return false;
		return true;
	}
}
