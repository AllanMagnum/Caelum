package br.com.caelum.financas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.financas.modelo.Conta;

@Stateless
public class ContaDao {

	@Inject
	EntityManager manager;

	public void adiciona(Conta conta) {
		manager.joinTransaction();
		this.manager.persist(conta);
	}

	public Conta busca(Integer id) {
		manager.joinTransaction();
		return this.manager.find(Conta.class, id);
	}
	
	public void altera(Conta conta){
		manager.joinTransaction();
		this.manager.merge(conta);
	}

	public List<Conta> lista() {
		manager.joinTransaction();
		return this.manager.createQuery("select c from Conta c", Conta.class)
				.getResultList();
	}

	public void remove(Conta conta) {
		manager.joinTransaction();
		Conta contaParaRemover = this.manager.find(Conta.class, conta.getId());
		this.manager.remove(contaParaRemover);
	}

}




