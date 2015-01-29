package br.com.caelum.financas.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.caelum.financas.exception.ValorInvalidoException;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.modelo.ValorPorMesEAno;

@Stateless
public class MovimentacaoDao {

	@Inject
	EntityManager manager;

	
	public void adiciona(Movimentacao movimentacao) {
		manager.joinTransaction();
		this.manager.persist(movimentacao);
		
		if(movimentacao.getValor().compareTo(BigDecimal.ZERO) < 0) {
			throw new ValorInvalidoException("Movimentacao negativa");
		}
	}

	public Movimentacao busca(Integer id) {
		manager.joinTransaction();
		return this.manager.find(Movimentacao.class, id);
	}

	public List<Movimentacao> lista() {
		manager.joinTransaction();
		return this.manager.createQuery("select m from Movimentacao m", Movimentacao.class).getResultList();
	}
	
	public List<Movimentacao> listaComCategorias(){
		manager.joinTransaction();
		return this.manager.createQuery("select distinct m from Movimentacao m left join fetch m.categorias", Movimentacao.class).getResultList();
	}
	
	public void remove(Movimentacao movimentacao) {
		manager.joinTransaction();
		Movimentacao movimentacaoParaRemover = this.manager.find(Movimentacao.class, movimentacao.getId());
		this.manager.remove(movimentacaoParaRemover);
	}
	
	public List<Movimentacao> listaTodasMovimentacoes(Conta conta){
		manager.joinTransaction();
		String jpql = "select m from Movimentacao m where m.conta = :conta";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("conta", conta);
		return query.getResultList();
	}
	
	public List<Movimentacao> listaPorValorETipo(BigDecimal valor,TipoMovimentacao tipo){
		manager.joinTransaction();
		String jpql = "select m from Movimentacao m where m.tipoMovimentacao = :tipo and m.valor <= :valor";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("valor", valor);
		query.setParameter("tipo", tipo);
		query.setHint("org.hibernate.cacheable", "true");
		return query.getResultList();
	}
	
	public BigDecimal calculaTotalMovimentado(Conta conta, TipoMovimentacao tipoMovimentacao){
		manager.joinTransaction();
		String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :conta and m.tipoMovimentacao = :tipoMovimentacao";
		TypedQuery<BigDecimal> query = this.manager.createQuery(jpql, BigDecimal.class);
		query.setParameter("conta", conta);
		query.setParameter("tipoMovimentacao", tipoMovimentacao);
		return query.getSingleResult();
	}

	public List<Movimentacao> buscaTodasMovimentacoesDaConta(String titular){
		manager.joinTransaction();
		String jpql = "select m from Movimentacao m where m.conta.titular like :titular";
		TypedQuery<Movimentacao> query = this.manager.createQuery(jpql, Movimentacao.class);
		query.setParameter("titular", "%" + titular + "%");
		return query.getResultList();
	}
	
	public List<ValorPorMesEAno> listaMesesComMovimentacoes(Conta conta, TipoMovimentacao tipoMovimentacao){
		manager.joinTransaction();
		String jpql = "select new br.com.caelum.financas.modelo.ValorPorMesEAno( year(m.data), month(m.data), sum(m.valor)) from Movimentacao m where m.conta = :conta and m.tipoMovimentacao = :tipoMovimentacao group by year(m.data), month(m.data)";
		TypedQuery<ValorPorMesEAno> query = manager.createQuery(jpql, ValorPorMesEAno.class);
		query.setParameter("conta", conta);
		query.setParameter("tipoMovimentacao", tipoMovimentacao);
		return query.getResultList();
	}
	
	public List<Movimentacao> listaTodasComCriteria(){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Movimentacao> criteria = builder.createQuery(Movimentacao.class);
		criteria.from(Movimentacao.class);
		return this.manager.createQuery(criteria).getResultList();
	}
	
	public BigDecimal somaMovimentacoesDoTitular(String titular){
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		Root<Movimentacao> root = criteria.from(Movimentacao.class);
		//criteria.select(builder.select(builder.sum(root.<BigDecimal>get("valor"))));
		return null;
	}
}
