package br.com.caelum.mvc.logica;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.dao.ContatoDao;
import br.com.caelum.agenda.modelo.Contato;

public class ListaContatoLogic implements Logica{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println(request.getAttribute("connection"));
		Connection connection = (Connection) request.getAttribute("connection");
		
		ContatoDao contatoDao = new ContatoDao(connection);
		List<Contato> contatos = contatoDao.getLista();
		
		request.setAttribute("contatos", contatos);
		
		return "lista-contatos.jsp";
	}

}
