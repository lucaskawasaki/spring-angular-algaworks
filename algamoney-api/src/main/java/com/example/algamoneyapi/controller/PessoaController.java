package com.example.algamoneyapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRespository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> selecionarPessoa(@PathVariable Long id){		
		Optional<Pessoa> pessoa = pessoaRespository.findById(id);
		
		return pessoa.isPresent() ? 
				ResponseEntity.ok(pessoa.get()) : 
					ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public List<Pessoa> listarPessoas(){
		
		return pessoaRespository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> salvarPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response)
	{
		Pessoa pessoaSalva = pessoaRespository.save(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{codigo}")
				.buildAndExpand(pessoaSalva.getId())
				.toUri();
			
		response.setHeader("Location", uri.toASCIIString());
		
		return  ResponseEntity.created(uri).body(pessoaSalva);
	}

}
