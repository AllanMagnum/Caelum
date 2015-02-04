package br.com.caelum.nostasfiscais.mb;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@Named
@ViewScoped
public class NotaFiscalBean {

	private NotaFiscal notaFiscal = new NotaFiscal();
	@Inject
	private NotaFiscalDao notaFiscalDao;
	@Inject
	private ProdutoDao produtoDao;
	private Item item = new Item();
	private Long idProduto;
	private Double soma=0.0;
	
	@Transactional
	public String gravar(){
		this.notaFiscalDao.adiciona(notaFiscal);
		this.notaFiscal = new NotaFiscal();
		return "notafiscal?faces-redirect=true";
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	public void guardaItem(){
		Produto produto = produtoDao.buscaPorId(idProduto);
		this.item.setProduto(produto);
		this.item.setValorUnitario(produto.getPreco());
		this.item.setNotaFiscal(this.notaFiscal);
		this.notaFiscal.getItens().add(this.item);
		this.item = new Item();
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	public Double getSoma(){
		for (Item item : this.notaFiscal.getItens()) {
			this.soma+=item.getTotal();
		}
		return this.soma;
	}
	
}
