package com.anschau.udemy.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.anschau.udemy.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	private String senha;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;

	public ClienteNewDTO() {
		
	}
	
	public ClienteNewDTO(String nome, String email, String cpfOuCnpj, Integer tipo, String logradouro, String numero,
			String complemento, String bairro, String cep, String telefone1, String telefone2, String telefone3,
			Integer cidadeId) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
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

	@NotEmpty(message="Nome do cliente é obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre {min} e {max} caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message="E-mail do cliente é obrigatório")
	@Email(message="E-mail inválido")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message="Preenchimento é obrigatório")
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	@NotEmpty(message="Preenchimento é obrigatório")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@NotEmpty(message="Preenchimento é obrigatório")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotEmpty(message="Preenchimento é obrigatório")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@NotEmpty(message="Preenchimento é obrigatório")
	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	@NotEmpty
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	
}
