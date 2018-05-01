package com.nelioalves.cursomc.domain.enums;

public enum enumEstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");

	private int cod;
	private String descricao;
	
	private enumEstadoPagamento(int var_cod, String var_descricao) {
		this.cod = var_cod;
		this.descricao = var_descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static enumEstadoPagamento toEnum(Integer var_cod) {
		if(var_cod == null) {
			return null;
		}
		for(enumEstadoPagamento x: enumEstadoPagamento.values()) {
			if(var_cod == x.getCod()) {
				return x;
			}
		}
		throw new IllegalArgumentException("ERRO_PADRAO#0026@IllegalArgumentException (enumErroPadrao): "+var_cod);
	}

}
