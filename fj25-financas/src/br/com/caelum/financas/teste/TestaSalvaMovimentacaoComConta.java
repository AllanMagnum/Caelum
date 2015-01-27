package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.EnumTipoMovimentacao;
import br.com.caelum.financas.modelo.Movimentacao;

public class TestaSalvaMovimentacaoComConta {
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlefinancas");
		EntityManager manager = emf.createEntityManager();
		
		Conta conta = new Conta();
		conta.setBanco("Bando santander");
		conta.setAgencia("99999-9");
		conta.setNumero("999");
		conta.setTitular("allan");
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		//movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("conta de luz - abril/2010");
		movimentacao.setValor(new BigDecimal(100));
		movimentacao.setTipoMovimentacao(EnumTipoMovimentacao.SAIDA);
		
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(manager);
		ContaDao contaDao = new ContaDao(manager);
		
		manager.getTransaction().begin();
		
		contaDao.adiciona(conta);
		movimentacaoDao.adiciona(movimentacao);
		
		manager.getTransaction().commit();
		manager.close();
	}
}
