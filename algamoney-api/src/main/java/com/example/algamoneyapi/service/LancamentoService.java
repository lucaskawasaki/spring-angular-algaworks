package com.example.algamoneyapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.repository.filter.LancementoFilter;
import com.example.algamoneyapi.repository.projection.ResumoLancamento;
import com.example.algamoneyapi.service.execption.PessoaInativaException;
import com.example.algamoneyapi.service.execption.PessoaInexistenteException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Page<Lancamento> listar(LancementoFilter filter, Pageable pageable){
		return lancamentoRepository.filtrar(filter, pageable);
	}
	
	public Page<ResumoLancamento> resumir(LancementoFilter filter, Pageable pageable){
		return lancamentoRepository.resumir(filter, pageable);
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

	public void removerLancamento(Long id) {
		lancamentoRepository.deleteById(id);		
	}

	public int selecionarQuantidade() {		
		return lancamentoRepository.findAll().size();
	}

}
