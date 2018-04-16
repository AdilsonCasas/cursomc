package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
 
@Entity
public class CidadeEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="estado_id")
	private EstadoEntity estado;
	
	public CidadeEntity() {
	}

	public CidadeEntity(Integer var_id, String var_nome, EstadoEntity var_estado) {
		super();
		this.id = var_id;
		this.nome = var_nome;
		this.estado = var_estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer var_id) {
		this.id = var_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String var_nome) {
		this.nome = var_nome;
	}

	public EstadoEntity getEstado() {
		return estado;
	}

	public void setEstado(EstadoEntity var_estado) {
		this.estado = var_estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CidadeEntity other = (CidadeEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
