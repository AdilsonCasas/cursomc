package com.nelioalves.cursomc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nelioalves.cursomc.domain.enums.enumEstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartaoDomain extends PagamentoDomain {
/*  Exemplo de arq. Json para processar uma inclusão de pedido com sub-classe e uso da anotação acima '@JsonTypeName("pagamentoComCartao")'
{
	"cliente" : {"id" : 1},
	"enderecoDeEntrega" : {"id" : 1},
	"pagamento" : {
		"numeroDeParcelas" : 10,
		"@type": "pagamentoComCartao"
	},
	"itens" : [
		{
		"quantidade" : 2,
		"produto" : {"id" : 3}
		},
		{
		"quantidade" : 1,
		"produto" : {"id" : 1}
		}
	]
}
*/

	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentoComCartaoDomain() {
	}

	public PagamentoComCartaoDomain(Integer var_id, enumEstadoPagamento var_estado, PedidoDomain var_pedido, Integer var_numeroDeParcelas) {
		super(var_id, var_estado, var_pedido);
		this.numeroDeParcelas = var_numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return this.numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer var_numeroDeParcelas) {
		this.numeroDeParcelas = var_numeroDeParcelas;
	}
}
