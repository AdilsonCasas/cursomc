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
public class EnderecoEntity implements Serializable {

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
	private ClienteEntity cliente;

	@ManyToOne
	@JoinColumn(name="cidade_id")
	private CidadeEntity cidade;
	
	public EnderecoEntity() {
	}

	public EnderecoEntity(Integer var_id, String var_logradouro, String var_numero, String var_complemento, String var_bairro, String var_cep, ClienteEntity var_cliente, CidadeEntity var_cidade) {
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

	public ClienteEntity getCliente() {
		return this.cliente;
	}

	public void setCliente(ClienteEntity var_cliente) {
		this.cliente = var_cliente;
	}

	public CidadeEntity getCidade() {
		return this.cidade;
	}

	public void setCidade(CidadeEntity var_cidade) {
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
		EnderecoEntity other = (EnderecoEntity) obj;
		if (this.Id == null) {
			if (other.Id != null)
				return false;
		} else if (!this.Id.equals(other.Id))
			return false;
		return true;
	}
}
