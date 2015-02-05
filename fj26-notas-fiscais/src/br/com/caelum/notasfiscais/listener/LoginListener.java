package br.com.caelum.notasfiscais.listener;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.modelo.Usuario;
import br.com.caelum.notasfiscais.tx.EmailComercio;


public class LoginListener {
	
	@Inject @EmailComercio
	private String emailComercial;
	
	public void onLogin(@Observes Usuario usuario){
		System.out.printf("Usuario %s se logou no sistema. ", usuario.getLogin());
		System.out.println(emailComercial);
	}
}
