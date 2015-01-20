package br.com.caelum.argentum.reader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {
		String xmlDeTeste = "<list>" +
	                        	"<negociacao>" +
	                            	"<preco>43.5</preco>" +
	                                "<quantidade>1000</quantidade>" +
	                              	"<data>" +
	                              		"<time>1322233344455</time>" +
	                              	"</data>" +
	                            "</negociacao>" +
	                         "</list>"; //o XML vai aqui!
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		Assert.assertEquals(1, negociacoes.size());
		
		for (Negociacao negociacao : negociacoes) {
			Assert.assertEquals(43.5, negociacao.getPreco(), 0.00001);
			Assert.assertEquals(1000, negociacao.getQuantidade());
		}
		
		
		
	}
	
	@Test
	public void carregaXmlSemNegociacaoEmListaUnitaria() {
		String xmlDeTeste = ""; //o XML vai aqui!
		
		LeitorXML leitor = new LeitorXML();
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		Assert.assertEquals(0, negociacoes.size());
		
		for (Negociacao negociacao : negociacoes) {
			Assert.assertEquals(43.5, negociacao.getPreco(), 0.00001);
			Assert.assertEquals(1000, negociacao.getQuantidade());
		}
		
		
		
	}

}
