package br.com.caelum.argentum.reader;

import java.io.InputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXML {
	
	public List<Negociacao> carrega(InputStream inputStrean){
		XStream strean = new XStream(new DomDriver());
		strean.alias("negociacao", Negociacao.class);
		return (List<Negociacao>) strean.fromXML(inputStrean);
	}
}
