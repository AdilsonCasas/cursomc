package com.nelioalves.cursomc.domain;

import javax.persistence.Entity;

import com.nelioalves.cursomc.domain.enums.enumEstadoPagamento;

@Entity
public class domainPagamentoComCartao extends domainPagamento {

	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public domainPagamentoComCartao() {
	}

	public domainPagamentoComCartao(Integer id, enumEstadoPagamento estado, domainPedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return this.numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
}
