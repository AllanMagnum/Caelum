package br.com.caelum.jdbc.teste;

import java.util.List;

import br.com.caelum.jdbc.dao.ContatoDAO;
import br.com.caelum.jdbc.modelo.Contato;

public class TestaLista {

	public static void main(String[] args) {
		
		ContatoDAO contatoDAO = new ContatoDAO();
		List<Contato> contatos = contatoDAO.getLista();
		
		for (Contato contato : contatos) {
			System.out.println(contato);
		}
		
		Contato contato = new Contato();
		contato.setNome("ca");
		contatoDAO = new ContatoDAO();
		List<Contato> contatos1 = contatoDAO.buscarPorNome(contato);
		
		for (Contato contato1 : contatos1) {
			System.out.println(contato1);
		}
		
		Contato contato1 = new Contato();
		contato1.setId(1L);
		contatoDAO = new ContatoDAO();
		List<Contato> contatos2 = contatoDAO.buscarPorId(contato1);
		
		for (Contato contato2 : contatos2) {
			System.out.println(contato2);
		}
	}

}
