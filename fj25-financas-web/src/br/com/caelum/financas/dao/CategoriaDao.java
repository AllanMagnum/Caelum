package br.com.caelum.financas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.caelum.financas.modelo.Categoria;

@Stateless
public class CategoriaDao {
	
	@Inject
	private EntityManager manager;
	
	public Categoria procura(Integer id){
		manager.joinTransaction();
		return manager.find(Categoria.class, id);
	}
	
	public List<Categoria> lista(){
		manager.joinTransaction();
		String jpql = "select c from Categoria c";
		TypedQuery<Categoria> query = manager.createQuery(jpql, Categoria.class);
		return query.getResultList();
	}
}
