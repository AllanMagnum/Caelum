package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

public class ContatoDAO {
	private Connection connection;
	private String sql;
	private Contato contato;
	
	public ContatoDAO(){
		this.connection = new ConnectionFactory().getConnection(); 
	}
	
	public void adiciona(Contato contato){
		this.sql = "insert into contato "
				 + "(nome, email, endereco, dataNascimento)"
				 + "values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(this.sql);
			
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contato> getLista(){
		try {
			this.sql = "select * from contato";
			 PreparedStatement stmt = connection.prepareStatement(this.sql);
			 
			 List<Contato> contatos = new ArrayList<Contato>();
			 
			 ResultSet rs = stmt.executeQuery();
			 
			 while(rs.next()){
				 this.contato = new Contato();
				 this.contato.setId(rs.getLong("id"));
				 this.contato.setNome(rs.getString("nome"));
				 this.contato.setEmail(rs.getString("email"));
				 this.contato.setEndereco(rs.getString("endereco"));
				 Calendar dataNascimento = Calendar.getInstance();
				 dataNascimento.setTime(rs.getDate("dataNascimento"));
				 this.contato.setDataNascimento(dataNascimento);
				 
				 contatos.add(this.contato);
			 }
			 
			 rs.close();
			 stmt.close();
			 return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	 
		 
	}
	
	public List<Contato> buscarPorNome(Contato contato){
		try {
			this.sql = "select * from contato where nome like ?";
			 PreparedStatement stmt = connection.prepareStatement(this.sql);
			 
			 List<Contato> contatos = new ArrayList<Contato>();
			 
			 stmt.setString(1, contato.getNome() + "%");
			 ResultSet rs = stmt.executeQuery();
			 
			 
			 while(rs.next()){
				 this.contato = new Contato();
				 this.contato.setId(rs.getLong("id"));
				 this.contato.setNome(rs.getString("nome"));
				 this.contato.setEmail(rs.getString("email"));
				 this.contato.setEndereco(rs.getString("endereco"));
				 Calendar dataNascimento = Calendar.getInstance();
				 dataNascimento.setTime(rs.getDate("dataNascimento"));
				 this.contato.setDataNascimento(dataNascimento);
				 
				 contatos.add(this.contato);
			 }
			 
			 rs.close();
			 stmt.close();
			 return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	 
		 
	}
	
	public List<Contato> buscarPorId(Contato contato){
		try {
			this.sql = "select * from contato where id = ?";
			 PreparedStatement stmt = connection.prepareStatement(this.sql);
			 
			 List<Contato> contatos = new ArrayList<Contato>();
			 
			 stmt.setLong(1, contato.getId());
			 ResultSet rs = stmt.executeQuery();
			 
			 
			 while(rs.next()){
				 this.contato = new Contato();
				 this.contato.setId(rs.getLong("id"));
				 this.contato.setNome(rs.getString("nome"));
				 this.contato.setEmail(rs.getString("email"));
				 this.contato.setEndereco(rs.getString("endereco"));
				 Calendar dataNascimento = Calendar.getInstance();
				 dataNascimento.setTime(rs.getDate("dataNascimento"));
				 this.contato.setDataNascimento(dataNascimento);
				 
				 contatos.add(this.contato);
			 }
			 
			 rs.close();
			 stmt.close();
			 return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	 
		 
	}
}
