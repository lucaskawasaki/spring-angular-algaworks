package com.example.algamoneyapi.service;

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
	
	public Pessoa atualizar(long id, Pessoa pessoa) {
		Optional<Pessoa> pessoaSalva = pessoaRespository.findById(id);
		
		if(pessoaSalva.isPresent() == false) {
			throw new EmptyResultDataAccessException("Pessoa não encontrada", 1);
		}
		
		Pessoa pessoaAlterada = pessoaSalva.get();		
		BeanUtils.copyProperties(pessoa, pessoaAlterada, "id");		
		
		return pessoaRespository.save(pessoaAlterada);
	}

}
