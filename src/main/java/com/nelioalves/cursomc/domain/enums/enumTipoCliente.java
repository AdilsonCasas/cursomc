package com.nelioalves.cursomc.domain.enums;

public enum enumTipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;
	
	private enumTipoCliente(int var_cod, String var_descricao) {
		this.cod = var_cod;
		this.descricao = var_descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static enumTipoCliente toEnum(Integer var_cod) {
		if(var_cod == null) {
			return null;
		}
		for(enumTipoCliente x: enumTipoCliente.values()) {
			if(var_cod == x.getCod()) {
				return x;
			}
		}
		throw new IllegalArgumentException("ERRO_PADRAO#0028@IllegalArgumentException (enumTipoCliente): "+var_cod);
	}
}
