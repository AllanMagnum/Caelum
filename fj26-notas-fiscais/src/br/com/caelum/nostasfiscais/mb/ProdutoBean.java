package br.com.caelum.nostasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@Named
@ViewScoped
public class ProdutoBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Produto produto;
	@Inject
	private ProdutoDao produtoDao;
	private List<Produto> produtos;
	private Double soma = 0.0;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getProdutos() {
		if(produtos == null){
			this.produtos = produtoDao.listaTodos();
		}
		return this.produtos;
	}
	
	public void cancela(){
		this.produto = new Produto();
	}

	public void grava(){
		if(produto.getId() == null){
			produtoDao.adiciona(produto);
		}
		else{
			produtoDao.atualiza(produto);
		}
	
		this.produtos = produtoDao.listaTodos();
		this.produto = new Produto();
	}
	
	public void remove(Produto produto){
		produtoDao.remove(produto);
		this.produtos = produtoDao.listaTodos();
	}
	
	public Double getSoma(){
		for (Produto produto : this.produtos) {
			this.soma+=produto.getPreco();
		}
		return this.soma;
	}
}
