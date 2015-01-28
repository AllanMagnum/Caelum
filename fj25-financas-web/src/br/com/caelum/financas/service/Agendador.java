package br.com.caelum.financas.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.servlet.jsp.jstl.core.Config;

@Stateless
public class Agendador {

	private static int totalCriado;
	@Resource
	private TimerService timerService;

	@PostConstruct
	void posConstrucao(){
		System.out.println("criando agendador");
		totalCriado++;
	}
	
	@PreDestroy
	void preDestruicao(){
		System.out.println("destruicao agendador");
	}
	
	public void executa() {
		System.out.printf("%d instancias criadas %n", totalCriado);

		// simulando demora de 4s na execucao
		try {
			System.out.printf("Executando %s %n", this);
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
	}
	
	public void agenda(String expressaMinutos, String expressaSegundos){
		ScheduleExpression se = new ScheduleExpression();
		se.hour("*");
		se.minute(expressaMinutos);
		se.second(expressaSegundos);
		
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setInfo(se.toString());
		timerConfig.setPersistent(false);
		
		this.timerService.createCalendarTimer(se, timerConfig);
		
		System.out.println("Agendamento: " + se);
	}
	
	@Timeout
	public void verificacaoPeriodicaSeHaNovasContas(Timer timer){
		System.out.println(timer.getInfo());
	}

}
