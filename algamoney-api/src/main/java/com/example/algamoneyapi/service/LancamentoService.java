package com.example.algamoneyapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.service.execption.PessoaInativaException;
import com.example.algamoneyapi.service.execption.PessoaInexistenteException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<Lancamento> listar(){
		return lancamentoRepository.findAll();
	}
	
	public Lancamento buscarPorID(long id) {
		Optional<Lancamento>lancamento = lancamentoRepository.findById(id);
		
		if(lancamento.isPresent()) {
			return lancamento.get();
		}
		
		throw new EmptyResultDataAccessException("Lancamento não encontrado", 1);
	}
	
	public Lancamento salvar (Lancamento lancamento) {
		
		verificarPessoaValida(lancamento);
		
		return lancamentoRepository.save(lancamento);
	}

	private void verificarPessoaValida(Lancamento lancamento) {
		Optional<Pessoa> pessoaRetornada = pessoaRepository.findById(lancamento.getPessoa().getId());
		
		if(pessoaRetornada.isPresent() == false) {
			throw new PessoaInexistenteException();			
		}
		
		Pessoa pessoa = pessoaRetornada.get();
		
		if(pessoa.isAtivo() == false) {
			throw new PessoaInativaException();
		}
	}

}
