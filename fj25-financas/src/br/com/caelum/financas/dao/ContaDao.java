package br.com.caelum.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;

public class ContaDao {
	private EntityManager manager;
	
	public ContaDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public void adiciona(Conta conta){
		this.manager.persist(conta);
	}
	
	public void remove(Conta conta){
		this.manager.remove(conta);
	}
	
	public Conta buscar(Integer id){
		return this.manager.find(Conta.class, id);
	}
	
	public List<Conta> lista(){
		return this.manager.createQuery("select c from Conta c", Conta.class).getResultList();
	}
}
