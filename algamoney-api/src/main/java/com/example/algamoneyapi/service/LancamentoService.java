package com.example.algamoneyapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository repository;
	
	public List<Lancamento> listar(){
		return repository.findAll();
	}
	
	public Lancamento buscarPorID(long id) {
		Optional<Lancamento>lancamento = repository.findById(id);
		
		if(lancamento.isPresent()) {
			return lancamento.get();
		}
		
		throw new EmptyResultDataAccessException("Lancamento não encontrado", 1);
	}
	
	public Lancamento salvar (Lancamento lancamento) {
		return repository.save(lancamento);
	}

}
