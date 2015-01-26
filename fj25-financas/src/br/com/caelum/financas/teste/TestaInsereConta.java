package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;

public class TestaInsereConta {
	public static void main(String[] args){
		long inicio = System.currentTimeMillis();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlefinancas");
		EntityManager manager = emf.createEntityManager();
		
		Conta conta = new Conta();
		conta.setTitular("allan");
		conta.setBanco("Bando do Brasil");
		conta.setAgencia("12345-6");
		conta.setNumero("0999");
		
		ContaDao contaDao = new ContaDao(manager);
		
		manager.getTransaction().begin();
		
		contaDao.adiciona(conta);
		
		manager.getTransaction().commit();
		manager.close();
		
		System.out.println("Conta gravada com sucesso!");
		
		long fim = System.currentTimeMillis();
		System.out.println("Executado em: " + (fim - inicio) + "ms");
	}
}
