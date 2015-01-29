package br.com.caelum.financas.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ApplicationScoped
public class EntityManagerProducer {
	
	@PersistenceUnit
	private EntityManagerFactory factory;
	
	@Produces @RequestScoped
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	
	@Produces @ApplicationScoped
	public Cache getCache(){
		return factory.getCache();
	}
	
	public void close(@Disposes EntityManager manager){
		manager.close();
	}
}
