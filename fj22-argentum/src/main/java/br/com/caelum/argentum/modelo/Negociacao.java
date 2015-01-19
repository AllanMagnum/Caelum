package br.com.caelum.argentum.modelo;
import java.util.Calendar;


public final class Negociacao {
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negociacao(double preco, int quantidade, Calendar data) {
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) this.data.clone();
	}

	public double getVolume(){
		return this.preco * this.quantidade;
	}
	
	@Override
	public String toString() {
		return "Negociacao [preco=" + preco + ", quantidade=" + quantidade
				+ ", data=" + data + "]";
	}
	
	
}
