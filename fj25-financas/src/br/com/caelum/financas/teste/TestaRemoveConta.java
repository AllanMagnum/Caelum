package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;

public class TestaRemoveConta {
	public static void main(String[] args){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlefinancas");
		EntityManager manager = emf.createEntityManager();
		
		ContaDao contaDao = new ContaDao(manager);
		
		manager.getTransaction().begin();
		
		Conta conta = contaDao.buscar(1);
		
		contaDao.remove(conta);
		
		manager.getTransaction().commit();
		manager.close();

	}
}
