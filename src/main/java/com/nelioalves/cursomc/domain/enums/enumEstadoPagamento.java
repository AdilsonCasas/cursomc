package com.nelioalves.cursomc.domain.enums;

public enum enumEstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");

	private int cod;
	private String descricao;
	
	private enumEstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static enumEstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(enumEstadoPagamento x: enumEstadoPagamento.values()) {
			if(cod == x.getCod()) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Cod EstadoPagamento inválido: "+cod);
	}

}
