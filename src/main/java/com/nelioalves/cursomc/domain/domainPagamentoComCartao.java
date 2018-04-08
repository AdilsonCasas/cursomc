package com.nelioalves.cursomc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nelioalves.cursomc.domain.enums.enumEstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class domainPagamentoComCartao extends domainPagamento {
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
