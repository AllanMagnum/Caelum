package br.com.caelum.financas.mb;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;

@Named
@ApplicationScoped
public class EstatisticasBean {

	@Inject
	private EntityManager manager;
	private Statistics estatisticas;
	
	public Statistics getEstatisticas() {
		return estatisticas;
	}

	public void setEstatisticas(Statistics estatistica) {
		this.estatisticas = estatistica;
	}

	public void gera() {
		Session session = this.manager.unwrap(Session.class);		
		this.estatisticas = session.getSessionFactory().getStatistics();
	}
}
