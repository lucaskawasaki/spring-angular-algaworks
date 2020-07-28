package com.example.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoneyapi.model.Lancamento;


public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
