package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelioalves.cursomc.domain.enums.enumPerfilUsuario;
import com.nelioalves.cursomc.domain.enums.enumTipoCliente;

@Entity
public class ClienteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer Id;
	private String nome;
	
	@Column(unique=true) //unique sendo true garante que no BD não haverá dois email's iguais, porém é necessário fazer um controle de exception personalizada.
	private String email;
	private String CpfOuCnpj;
	private Integer tipoCliente;
	@JsonIgnore
	private String senha;

	// Sempre que houver uma relação do tipo "Um pra Muitos" no BD, ao tentar excluir esta entidade vai gerar um erro do tipo "DataIntegrityViolationException"
	// e por isso se coloca a diretiva 'cascade' na relação, para excluir em 'cascata' o outro obj relacionado
	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
	private List<EnderecoEntity> enderecos = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="TELEFONE_ENTITY")
	private Set<String> telefones = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS_ENTITY")
	private Set<Integer> perfis = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	private List<PedidoEntity> pedidos = new ArrayList<>();
	
	public ClienteEntity() {
		addPerfil(enumPerfilUsuario.CLIENTE); // vou assumir como regra de negácio que todo usuário do meu sistema será um "Cliente", dentre estes, alguns, também serão ADMIN
	}

	public ClienteEntity(Integer var_id, String var_nome, String var_email, String var_cpfOuCnpj, enumTipoCliente var_tipoCliente, String var_senha) {
		super();
		this.Id = var_id;
		this.nome = var_nome;
		this.email = var_email;
		this.CpfOuCnpj = var_cpfOuCnpj;
		this.tipoCliente = (var_tipoCliente == null) ? null : var_tipoCliente.getCod();
		this.senha = var_senha;
		addPerfil(enumPerfilUsuario.CLIENTE); // vou assumir como regra de negácio que todo usuário do meu sistema será um "Cliente", dentre estes, alguns, também serão ADMIN
	}

	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer var_id) {
		this.Id = var_id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String var_nome) {
		this.nome = var_nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String var_email) {
		this.email = var_email;
	}

	public String getCpfOuCnpj() {
		return this.CpfOuCnpj;
	}

	public void setCpfOuCnpj(String var_cpfOuCnpj) {
		this.CpfOuCnpj = var_cpfOuCnpj;
	}

	public enumTipoCliente getTipoCliente() {
		return enumTipoCliente.toEnum(this.tipoCliente);
	}

	public void setTipoCliente(enumTipoCliente var_enum_tipoCliente) {
		this.tipoCliente = var_enum_tipoCliente.getCod();
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String var_senha) {
		this.senha = var_senha;
	}
	
	public Set<enumPerfilUsuario> getPerfis() {
		return this.perfis.stream().map(x -> enumPerfilUsuario.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(enumPerfilUsuario var_enum_perfil) {
		this.perfis.add(var_enum_perfil.getCod());
	}

	public List<EnderecoEntity> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<EnderecoEntity> var_enderecos) {
		this.enderecos = var_enderecos;
	}

	public Set<String> getTelefones() {
		return this.telefones;
	}

	public void setTelefones(Set<String> var_telefones) {
		this.telefones = var_telefones;
	}

	public List<PedidoEntity> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<PedidoEntity> var_pedidos) {
		this.pedidos = var_pedidos;
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
		ClienteEntity other = (ClienteEntity) obj;
		if (this.Id == null) {
			if (other.Id != null)
				return false;
		} else if (!this.Id.equals(other.Id))
			return false;
		return true;
	}
}
