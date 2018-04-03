package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelioalves.cursomc.domain.enums.enumTipoCliente;

@Entity
public class domainCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	private String nome;
	private String email;
	private String CpfOuCnpj;
	private Integer tipoCliente;
	
	@OneToMany(mappedBy="cliente")
	private List<domainEndereco> enderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	private List<domainPedido> pedidos = new ArrayList<>();
	
	public domainCliente() {
	}

	public domainCliente(Integer id, String nome, String email, String cpfOuCnpj, enumTipoCliente tipoCliente) {
		super();
		this.Id = id;
		this.nome = nome;
		this.email = email;
		this.CpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = tipoCliente.getCod();
	}

	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer id) {
		this.Id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return this.CpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.CpfOuCnpj = cpfOuCnpj;
	}

	public enumTipoCliente getTipoCliente() {
		return enumTipoCliente.toEnum(this.tipoCliente);
	}

	public void setTipoCliente(enumTipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente.getCod();
	}

	public List<domainEndereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<domainEndereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return this.telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public List<domainPedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<domainPedido> pedidos) {
		this.pedidos = pedidos;
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
		domainCliente other = (domainCliente) obj;
		if (this.Id == null) {
			if (other.Id != null)
				return false;
		} else if (!this.Id.equals(other.Id))
			return false;
		return true;
	}
}
