package com.example.algamoneyapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LancementoFilter {
	
	private String descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVencimentoInicial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVencimentoFinal;
	
	public LocalDate getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}
	
	public LocalDate getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDataVencimentoFinal(LocalDate dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}
	
	public void setDataVencimentoInicial(LocalDate dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
