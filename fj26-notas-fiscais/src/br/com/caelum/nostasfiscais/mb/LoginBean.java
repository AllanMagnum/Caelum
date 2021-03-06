package br.com.caelum.nostasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@RequestScoped
@Named
public class LoginBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private UsuarioLogadoBean usuarioLogado;
	@Inject
	private Event<Usuario> eventoLogin;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String efetuaLogin(){
		boolean existe = this.usuarioDao.existe(this.usuario);
		if(existe){
			eventoLogin.fire(this.usuario);
			return "listanotas?faces-redirect=true";
		}else{
			usuarioLogado.deslogar();
			this.usuario = new Usuario();
			return "login";
		}
	}
	
	public String logout(){
		usuarioLogado.deslogar();
		return "login";
	}
}
