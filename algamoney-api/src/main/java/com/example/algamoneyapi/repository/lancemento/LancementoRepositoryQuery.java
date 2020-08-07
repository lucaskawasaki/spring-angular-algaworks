package com.example.algamoneyapi.repository.lancemento;

import java.util.List;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.filter.LancementoFilter;

public interface LancementoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancementoFilter lancamentoFilter);

}
