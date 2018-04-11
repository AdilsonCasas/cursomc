package com.nelioalves.cursomc.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.nelioalves.cursomc.services.validation.Service_Annotation_ClienteInsert;

// Este '@' abaixo é uma 'annotation' que é usada para fazer validações de campos, como CNPJ ou CPF
@Service_Annotation_ClienteInsert
public class DTO_ClienteDomain_Completo implements Serializable {

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
	@NotEmpty(message="Preenchimento da SENHA do Cliente é obrigatório.")
	private String senha;

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
	
	public DTO_ClienteDomain_Completo() {
	}

	public DTO_ClienteDomain_Completo(String var_nome, String var_email, String var_cpfOuCnpj, Integer var_tipoCliente,
			String var_logradouro, String var_numero, String var_complemento, String var_bairro, String var_cep, String var_telefone1,
			String var_telefone2, String var_telefone3, Integer var_cidadeId) {
		super();
		this.nome = var_nome;
		this.email = var_email;
		this.CpfOuCnpj = var_cpfOuCnpj;
		this.tipoCliente = var_tipoCliente;
		this.logradouro = var_logradouro;
		this.numero = var_numero;
		this.complemento = var_complemento;
		this.bairro = var_bairro;
		this.cep = var_cep;
		this.telefone1 = var_telefone1;
		this.telefone2 = var_telefone2;
		this.telefone3 = var_telefone3;
		this.cidadeId = var_cidadeId;
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

	public Integer getTipoCliente() {
		return this.tipoCliente;
	}

	public void setTipoCliente(Integer var_tipoCliente) {
		this.tipoCliente = var_tipoCliente;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String var_senha) {
		this.senha = var_senha;
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

	public String getTelefone1() {
		return this.telefone1;
	}

	public void setTelefone1(String var_telefone1) {
		this.telefone1 = var_telefone1;
	}

	public String getTelefone2() {
		return this.telefone2;
	}

	public void setTelefone2(String var_telefone2) {
		this.telefone2 = var_telefone2;
	}

	public String getTelefone3() {
		return this.telefone3;
	}

	public void setTelefone3(String var_telefone3) {
		this.telefone3 = var_telefone3;
	}

	public Integer getCidadeId() {
		return this.cidadeId;
	}

	public void setCidadeId(Integer var_cidadeId) {
		this.cidadeId = var_cidadeId;
	}
}