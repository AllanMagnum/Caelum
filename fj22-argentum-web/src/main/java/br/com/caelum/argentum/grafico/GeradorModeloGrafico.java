package br.com.caelum.argentum.grafico;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class GeradorModeloGrafico {
	private SerieTemporal serie;
	private int inicio;
	private int fim;
	private CartesianChartModel grafico;
	
	public GeradorModeloGrafico(SerieTemporal serie, int inicio, int fim){
		this.serie = serie;
		this.inicio = inicio;
		this.fim = fim;
		this.grafico = new CartesianChartModel();
	}

	public void plotaIndicador(Indicador indicador){
		LineChartSeries chartSeries = new LineChartSeries(indicador.toString());
		
		for (int i = inicio; i <= fim; i++) {
			double valor = indicador.calcula(i, this.serie);
			chartSeries.set(i, valor);
		}
		
		this.grafico.addSeries(chartSeries);
	}
	
	public ChartModel getModeloGrafico(){
		return this.grafico;
	}
	
}
