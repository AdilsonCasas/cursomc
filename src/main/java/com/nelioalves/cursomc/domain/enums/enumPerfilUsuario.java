package com.nelioalves.cursomc.domain.enums;

public enum enumPerfilUsuario {
	
	ADMIN(1, "ROLE_ADMIN"), // este nome que inicia com "ROLE_" é uma obrigação do sistema de segurança do spring, não altere este nome
	CLIENTE(2, "ROLE_CLIENTE");

	private int cod;
	private String descricao;
	
	private enumPerfilUsuario(int var_cod, String var_descricao) {
		this.cod = var_cod;
		this.descricao = var_descricao;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static enumPerfilUsuario toEnum(Integer var_cod) {
		if(var_cod == null) {
			return null;
		}
		for(enumPerfilUsuario x: enumPerfilUsuario.values()) {
			if(var_cod == x.getCod()) {
				return x;
			}
		}
		throw new IllegalArgumentException("ERRO_PADRAO#0027@IllegalArgumentException (enumPerfilUsuario): "+var_cod);
	}

}
