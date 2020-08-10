package com.example.algamoneyapi.repository.lancemento;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.filter.LancementoFilter;

public interface LancementoRepositoryQuery {
	
	public Page<Lancamento> filtrar(LancementoFilter lancamentoFilter, Pageable pageable);

}
