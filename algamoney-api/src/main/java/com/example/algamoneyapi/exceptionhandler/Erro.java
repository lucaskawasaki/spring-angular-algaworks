package com.example.algamoneyapi.exceptionhandler;

public class Erro{
	private String mensagemUsuario;
	private String mensagemDesenvolvedor;
	
	public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		this.mensagemUsuario = mensagemUsuario;
	}
	
	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}
	
	public String getMensagemUsuario() {
		return mensagemUsuario;
	}
}