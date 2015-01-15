package br.com.caelum.mvc.logica;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.dao.ContatoDao;
import br.com.caelum.agenda.modelo.Contato;

public class AlteraContatoLogic implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse resquest)
			throws Exception {
		Connection connection = (Connection) request.getAttribute("connection");
		
		Long id = Long.parseLong(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String dataNascimentoTxt = request.getParameter("dataNascimento");
		System.out.println(dataNascimentoTxt);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimentoDate = null;
		try {
			dataNascimentoDate = sdf.parse(dataNascimentoTxt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar dataNascimentoCalendar = Calendar.getInstance();
		dataNascimentoCalendar.setTime(dataNascimentoDate);
		
		Contato contato = new Contato();
		contato.setId(id);
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(dataNascimentoCalendar);
		
		
		
		ContatoDao contatoDao = new ContatoDao(connection);
		contatoDao.atualiza(contato);
		
		return "mvc?logica=ListaContatoLogic";
	}

}
