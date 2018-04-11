package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EnderecoDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private ClienteDomain cliente;

	@ManyToOne
	@JoinColumn(name="cidade_id")
	private CidadeDomain cidade;
	
	public EnderecoDomain() {
	}

	public EnderecoDomain(Integer var_id, String var_logradouro, String var_numero, String var_complemento, String var_bairro, String var_cep, ClienteDomain var_cliente, CidadeDomain var_cidade) {
		super();
		this.Id = var_id;
		this.logradouro = var_logradouro;
		this.numero = var_numero;
		this.complemento = var_complemento;
		this.bairro = var_bairro;
		this.cep = var_cep;
		this.cliente = var_cliente;
		this.setCidade(var_cidade);
	}

	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer var_id) {
		this.Id = var_id;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String var_logradouro) {
		this.logradouro = var_logradouro;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String var_numero) {
		this.numero = var_numero;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String var_complemento) {
		this.complemento = var_complemento;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String var_bairro) {
		this.bairro = var_bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String var_cep) {
		this.cep = var_cep;
	}

	public ClienteDomain getCliente() {
		return this.cliente;
	}

	public void setCliente(ClienteDomain var_cliente) {
		this.cliente = var_cliente;
	}

	public CidadeDomain getCidade() {
		return this.cidade;
	}

	public void setCidade(CidadeDomain var_cidade) {
		this.cidade = var_cidade;
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
		EnderecoDomain other = (EnderecoDomain) obj;
		if (this.Id == null) {
			if (other.Id != null)
				return false;
		} else if (!this.Id.equals(other.Id))
			return false;
		return true;
	}
}
