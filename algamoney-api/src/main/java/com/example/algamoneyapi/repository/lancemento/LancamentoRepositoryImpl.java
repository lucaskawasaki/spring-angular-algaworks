package com.example.algamoneyapi.repository.lancemento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.metamodel.Lancamento_;
import com.example.algamoneyapi.repository.filter.LancementoFilter;

public class LancamentoRepositoryImpl implements LancementoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Lancamento> filtrar(LancementoFilter lancamentoFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		
		Root<Lancamento> root = criteria.from(Lancamento.class);
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(LancementoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			predicates.add(
					builder.like(
						builder.lower(root.get("descricao")),
						"%" +
						lancamentoFilter.getDescricao().toLowerCase() +
						"%"
					)
			);
		}
		
		if(lancamentoFilter.getDataVencimentoInicial() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(
							root.get("dataVencimento"), 
							lancamentoFilter.getDataVencimentoInicial()
					)
			);
		}
		
		if(lancamentoFilter.getDataVencimentoFinal() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(
							root.get("dataVencimento"), 
							lancamentoFilter.getDataVencimentoFinal()
					)
			);
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}