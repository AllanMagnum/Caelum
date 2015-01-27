package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.dao.MovimentacaoDao;

public class TestaBuscaContaDaMovimentacao {
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlefinancas");
		EntityManager manager = emf.createEntityManager();
	
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(manager);
		
		System.out.println(movimentacaoDao.buscar(2).getConta().getTitular());
		
		manager.close();
	}
}
