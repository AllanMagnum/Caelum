package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.dao.ContaDao;

public class TestaPesquisaIdConta {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlefinancas");
		EntityManager manager = emf.createEntityManager();
		
		
		ContaDao contaDao = new ContaDao(manager);
		System.out.println(contaDao.buscar(1).getTitular());
		
		manager.close();

	}

}
