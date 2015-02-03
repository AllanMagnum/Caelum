package br.com.caelum.nostasfiscais.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;

@ManagedBean
public class ProdutoBean {
	private Produto produto = new Produto();
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
			ProdutoDao produtoDao = new ProdutoDao();
			this.produtos = produtoDao.listaTodos();
		}
		return this.produtos;
	}
	
	public void cancela(){
		this.produto = new Produto();
	}

	public void grava(){
		ProdutoDao produtoDao = new ProdutoDao();
		
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
		ProdutoDao produtoDao = new ProdutoDao();
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
