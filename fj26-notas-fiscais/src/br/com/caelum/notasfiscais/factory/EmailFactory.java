package br.com.caelum.notasfiscais.factory;

import javax.enterprise.inject.Produces;

import br.com.caelum.notasfiscais.tx.EmailComercio;
import br.com.caelum.notasfiscais.tx.EmailFinanceiro;

public class EmailFactory {
	@Produces @EmailComercio
	private String emailComercial = "comercial@uberdist.com.br";
	@Produces @EmailFinanceiro
	private String emailFinanceiro = "financeiro@uberdist.com.br";
}
