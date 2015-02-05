package br.com.caelum.nostasfiscais.mb;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.caelum.notasfiscais.datamodel.DataModelNotasFiscais;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@Named
@ViewScoped
public class ListaNotasFiscaisBean {

	@Inject
	private DataModelNotasFiscais dataModel;

	public LazyDataModel<NotaFiscal> getDataModel() {
		return dataModel;
	}
	
}
