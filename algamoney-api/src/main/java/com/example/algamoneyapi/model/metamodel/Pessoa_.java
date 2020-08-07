package com.example.algamoneyapi.model.metamodel;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.example.algamoneyapi.model.Endereco;
import com.example.algamoneyapi.model.Pessoa;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, Boolean> ativo;
	public static volatile SingularAttribute<Pessoa, Endereco> endereco;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, Long> id;

}

