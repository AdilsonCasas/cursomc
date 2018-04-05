package com.nelioalves.cursomc.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.services.validation.serviceAnnotation_ClienteInsert;

// Este '@' abaixo é uma 'annotation' que é usada para fazer validações de campos, como CNPJ ou CPF
@serviceAnnotation_ClienteInsert
public class domainDTO_Cliente_Completo implements Serializable {

	private static final long serialVersionUID = 1L;

	// as diretivas "@NotEmpty" e "@Length" abaixo é parte do "Bean Validate" que faz parte do Java EE, ele define a validação definida nestes parâmetros e coloca a "message" de retorno ao REST
	@NotEmpty(message="Preenchimento do Nome do Cliente é obrigatório.")
	@Length(min=5, max=120, message="O tamanho do Nome do Cliente deve estar entre 5 e 120 caracteres.")
	private String nome;

	@NotEmpty(message="Preenchimento do Email do Cliente é obrigatório.")
	@Email(message="Email inválido.")
	private String email;

	@NotEmpty(message="Preenchimento do CPF/CNPJ do Cliente é obrigatório.")
	private String CpfOuCnpj;
	private Integer tipoCliente;

	@NotEmpty(message="Preenchimento do Logradouro do Cliente é obrigatório.")
	private String logradouro;

	@NotEmpty(message="Preenchimento do Número do Endereço do Cliente é obrigatório.")
	private String numero;
	private String complemento;
	private String bairro;

	@NotEmpty(message="Preenchimento do CEP do Cliente é obrigatório.")
	private String cep;

	@NotEmpty(message="Preenchimento de pelo menos 1 Telefone do Cliente é obrigatório.")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;
	
	public domainDTO_Cliente_Completo() {
	}

	public domainDTO_Cliente_Completo(String nome, String email, String cpfOuCnpj, Integer tipoCliente,
			String logradouro, String numero, String complemento, String bairro, String cep, String telefone1,
			String telefone2, String telefone3, Integer cidadeId) {
		super();
		this.nome = nome;
		this.email = email;
		this.CpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = tipoCliente;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
		this.cidadeId = cidadeId;
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

	public Integer getTipoCliente() {
		return this.tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return this.telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return this.telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return this.telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return this.cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
}