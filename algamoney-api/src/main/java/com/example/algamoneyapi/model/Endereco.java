package com.example.algamoneyapi.model;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	public String logradouro;
	public String numero;
	public String complemento;
	public String bairro;
	public String cep;
	public String cidade;
	public String estado;

	public Endereco() {
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogadouro(String logadouro) {
		this.logradouro = logadouro;
	}

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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}