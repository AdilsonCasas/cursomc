package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class domainEstado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy="estado")
	private List<domainCidade> cidades = new ArrayList<>();
	
	public domainEstado() {
	}

	public domainEstado(Integer id, String nome) {
		super();
		this.Id = id;
		this.nome = nome;
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

	public List<domainCidade> getCidades() {
		return this.cidades;
	}

	public void setCidades(List<domainCidade> cidades) {
		this.cidades = cidades;
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
		domainEstado other = (domainEstado) obj;
		if (this.Id == null) {
			if (other.Id != null)
				return false;
		} else if (!this.Id.equals(other.Id))
			return false;
		return true;
	}

}