package com.example.algamoneyapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.event.RecursoCriadoEvent;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {	
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/{id}")
	public Pessoa selecionarPessoa(@PathVariable Long id){		
		return pessoaService.buscarPessoaPorId(id);
	}
	
	@GetMapping
	public List<Pessoa> listarPessoas(){
		
		return pessoaService.listarPessoas();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> salvarPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response)
	{
		Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerPessoa(@PathVariable Long id) {
		pessoaService.removerPessoa(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> alterarPessoa(@PathVariable long id, @Valid @RequestBody Pessoa pessoa){
				
		return ResponseEntity.ok(pessoaService.atualizar(id, pessoa));
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(id, ativo);
	}

}
