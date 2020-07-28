package com.example.algamoneyapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRespository;	
	
	public List<Pessoa> listarPessoas(){
		return pessoaRespository.findAll();
	}
	
	public Pessoa atualizar(long id, Pessoa pessoa) {
		Pessoa pessoaAlterada = buscarPessoaPorId(id);		
		BeanUtils.copyProperties(pessoa, pessoaAlterada, "id");
		
		return pessoaRespository.save(pessoaAlterada);
	}
	
	public void atualizarPropriedadeAtivo(Long id, boolean ativo) {
		Pessoa pessoaAlterada = buscarPessoaPorId(id);
		pessoaAlterada.setAtivo(ativo);
		
		pessoaRespository.save(pessoaAlterada);
	}
	
	public Pessoa salvarPessoa(Pessoa pessoa) {
		return pessoaRespository.save(pessoa);		
	}

	public Pessoa buscarPessoaPorId(long id) {
		Optional<Pessoa> pessoa = pessoaRespository.findById(id);
		
		if(pessoa.isPresent() == false) {
			throw new EmptyResultDataAccessException("Pessoa não encontrada", 1);
		}
		
		return pessoa.get();
	}
	
	public void removerPessoa(long id) {
		pessoaRespository.deleteById(id);
	}

}
