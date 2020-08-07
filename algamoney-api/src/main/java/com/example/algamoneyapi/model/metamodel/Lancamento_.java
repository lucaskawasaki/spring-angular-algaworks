package com.example.algamoneyapi.model.metamodel;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.example.algamoneyapi.model.Categoria;
import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.model.TipoLancamento;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lancamento.class)
public abstract class Lancamento_ {

	public static volatile SingularAttribute<Lancamento, String> observacao;
	public static volatile SingularAttribute<Lancamento, TipoLancamento> tipo;
	public static volatile SingularAttribute<Lancamento, LocalDate> dataPagamento;
	public static volatile SingularAttribute<Lancamento, Pessoa> pessoa;
	public static volatile SingularAttribute<Lancamento, LocalDate> dataVencimento;
	public static volatile SingularAttribute<Lancamento, Categoria> categoria;
	public static volatile SingularAttribute<Lancamento, BigDecimal> valor;
	public static volatile SingularAttribute<Lancamento, Long> id;
	public static volatile SingularAttribute<Lancamento, String> descricao;

}

