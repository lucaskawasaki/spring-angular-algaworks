package com.example.algamoneyapi.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.algamoneyapi.exceptionhandler.Erro;

import com.example.algamoneyapi.event.RecursoCriadoEvent;
import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.filter.LancementoFilter;
import com.example.algamoneyapi.service.LancamentoService;
import com.example.algamoneyapi.service.execption.PessoaInativaException;
import com.example.algamoneyapi.service.execption.PessoaInexistenteException;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {
	
	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public List<Lancamento> pesquisar(LancementoFilter filter){
		return lancamentoService.listar(filter);
	}
	
	@GetMapping("/{id}")
	public Lancamento selecionarLancamento(@PathVariable Long id){
		return lancamentoService.buscarPorID(id);
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> salvarLancamento(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	@ExceptionHandler({ PessoaInativaException.class })
	public ResponseEntity<Object> handlePessoaInativaException(PessoaInativaException ex){
		
		String mensagemUsuario = messageSource.getMessage("pessoa.inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();

		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({ PessoaInexistenteException.class })
	public ResponseEntity<Object> handlePessoaInexistenteException(PessoaInexistenteException ex){
		
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();

		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
	}

}
