package br.com.caelum.argentum.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.ws.ClienteWebService;

@ManagedBean
public class NegociacaoMB {

	private List<Negociacao> negociacoes;
	
	public NegociacaoMB(){
		negociacoes = new ClienteWebService().getNegociacoes();
	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}
	
}
