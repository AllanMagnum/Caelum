package br.com.caelum.agenda.filtro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.caelum.agenda.ConnectionFactory;

@WebFilter("/*")
public class FiltroConexao implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Connection connection=null;
		try {
			connection = new ConnectionFactory().getConnection();
			request.setAttribute("connection", connection);
			chain.doFilter(request, response);
			connection.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
