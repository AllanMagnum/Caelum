package br.com.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.modelo.Conta;

public class TestaListagemConta {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlefinancas");
		EntityManager manager = emf.createEntityManager();
		
		ContaDao contaDao = new ContaDao(manager);
		List<Conta> contas = contaDao.lista();
		
		for (Conta conta : contas) {
			System.out.println(conta.getNumero());
		}
		manager.close();

	}
}
