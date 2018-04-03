package com.nelioalves.cursomc.domain.enums;

public enum enumTipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;
	
	private enumTipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static enumTipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(enumTipoCliente x: enumTipoCliente.values()) {
			if(cod == x.getCod()) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Cod Tipo Cliente inválido: "+cod);
	}
}
