package com.nelioalves.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nelioalves.cursomc.domain.enums.enumEstadoPagamento;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoletoEntity extends PagamentoEntity {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVencimento;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento;

	public PagamentoComBoletoEntity() {
	}

	public PagamentoComBoletoEntity(Integer var_id, enumEstadoPagamento var_enum_estado, PedidoEntity var_pedido, Date var_dataVencimento, Date var_dataPagamento) {
		super(var_id, var_enum_estado, var_pedido);
		this.dataPagamento = var_dataPagamento;
		this.dataVencimento = var_dataVencimento;
	}

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date var_dataVencimento) {
		this.dataVencimento = var_dataVencimento;
	}

	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	public void setDataPagamento(Date var_dataPagamento) {
		this.dataPagamento = var_dataPagamento;
	}
}
