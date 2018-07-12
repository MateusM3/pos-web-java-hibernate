package br.edu.guairaca.hibernate.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.guairaca.hibernate.dao.CidadeDAO;
import br.edu.guairaca.hibernate.model.Cidade;

@Named(value = "cidadeConverter")
public class CidadeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CidadeDAO cidadeDAO;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String str) throws ConverterException {
		if (str == null || str.isEmpty()) {
			return null;
		}

		Cidade cidade = this.cidadeDAO.find(Long.parseLong(str));

		return cidade;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj) throws ConverterException {
		if (obj == null) {
			return "";
		}

		if (!(obj instanceof Cidade)) {
			return "";
		}

		Cidade cidade = (Cidade) obj;

		return cidade.getCodigo().toString();
	}
}