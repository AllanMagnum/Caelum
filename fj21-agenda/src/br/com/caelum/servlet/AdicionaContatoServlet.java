package br.com.caelum.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.agenda.dao.ContatoDao;
import br.com.caelum.agenda.modelo.Contato;

@WebServlet("/adicionaContato")
public class AdicionaContatoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("connection");
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String dataNascimentoTxt = request.getParameter("dataNascimento");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimentoDate = null;
		try {
			dataNascimentoDate = sdf.parse(dataNascimentoTxt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar dataNascimentoCalendar = Calendar.getInstance();
		dataNascimentoCalendar.setTime(dataNascimentoDate);
		
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(dataNascimentoCalendar);
		
		
		ContatoDao contatoDao = new ContatoDao(connection);
		contatoDao.adiciona(contato);
		
		/*PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Contato " + contato.getNome() + " adicionado com sucesso");
		out.println("</body>");
		out.println("<html>");*/
		
		RequestDispatcher rd = request.getRequestDispatcher("/contato-adicionado.jsp");
		
		rd.forward(request, response);
		
	}
}
