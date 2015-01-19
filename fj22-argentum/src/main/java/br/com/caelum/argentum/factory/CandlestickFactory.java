package br.com.caelum.argentum.factory;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.modelo.Candlestick;
import br.com.caelum.argentum.modelo.Negociacao;

public class CandlestickFactory {
	public Candlestick constroiCandleParaData(Calendar data, List<Negociacao> negociacoes){
		double volume = 0;
		double maximo = 0;
		double minimo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		
		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();
			
			if(negociacao.getPreco() > maximo){
				maximo = negociacao.getPreco();
			}else if(negociacao.getPreco() < minimo){
				minimo = negociacao.getPreco();
			}
		}
		
		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 :  negociacoes.get(negociacoes.size()-1).getPreco();
		
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}
}
