package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandleFactory {

	public Candle constroiCandleParaData(Calendar data, List<Negociacao> negocios) {

		double maximo = Double.MIN_VALUE;
		double minimo = Double.MAX_VALUE;
		double volume = 0;

		for (Negociacao negocio : negocios) {
			volume += negocio.getVolume();

			double preco = negocio.getPreco();
			
			if (preco > maximo) {
				maximo = preco;
			} 
			if (preco < minimo) {
				minimo = preco;
			}
		}

		double abertura = negocios.isEmpty() ? 0 : negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty() ? 0 : negocios.get(negocios.size() - 1).getPreco();

		return new Candle(abertura, fechamento, minimo, maximo, volume, data);
	}

	public List<Candle> constroiCandles(List<Negociacao> todasNegociacoes) {
		List<Candle> candles = new ArrayList<Candle>();

		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
		Calendar dataAtual = todasNegociacoes.get(0).getData();

		for (Negociacao negociacao : todasNegociacoes) {
			// se não for mesmo dia, fecha candle e reinicia variáveis
			if (!negociacao.isMesmoDia(dataAtual)) {
				Candle candleDoDia = criaEGuardaCandle(negociacoesDoDia,
						dataAtual);
				candles.add(candleDoDia);
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacoesDoDia.add(negociacao);
		}
		Candle candleDoDia = criaEGuardaCandle(negociacoesDoDia, dataAtual);
		candles.add(candleDoDia);

		return candles;
	}

	private Candle criaEGuardaCandle(List<Negociacao> negociacoesDoDia,
			Calendar dataAtual) {
		Candle candleDoDia = constroiCandleParaData(dataAtual, 
												  negociacoesDoDia);
		return candleDoDia;
	}
}
