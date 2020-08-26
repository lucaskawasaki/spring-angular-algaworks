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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.algamoneyapi.model.Categoria_;
import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Lancamento_;
import com.example.algamoneyapi.model.Pessoa_;
import com.example.algamoneyapi.repository.filter.LancementoFilter;
import com.example.algamoneyapi.repository.projection.ResumoLancamento;

public class LancamentoRepositoryImpl implements LancementoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Lancamento> filtrar(LancementoFilter lancamentoFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		
		Root<Lancamento> root = criteria.from(Lancamento.class);
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		List<Lancamento> lancamento = query.getResultList();
		
		return new PageImpl<Lancamento>(lancamento, pageable, lancamento.size());
	}	

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
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

	@Override
	public Page<ResumoLancamento> resumir(LancementoFilter lancamentoFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		criteria.select(builder.construct(ResumoLancamento.class, 
										  root.get(Lancamento_.id),
										  root.get(Lancamento_.descricao),
										  root.get(Lancamento_.dataVencimento),
										  root.get(Lancamento_.dataPagamento),
										  root.get(Lancamento_.valor),
										  root.get(Lancamento_.tipo),
										  root.get(Lancamento_.categoria).get(Categoria_.nome),
										  root.get(Lancamento_.pessoa).get(Pessoa_.nome)));
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		List<ResumoLancamento> resultList = query.getResultList();
		
		return new PageImpl<>(resultList, pageable, resultList.size());
	}

}
