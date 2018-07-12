package br.edu.guairaca.hibernate.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.guairaca.hibernate.dao.EstadoDAO;
import br.edu.guairaca.hibernate.model.Estado;

@Named(value = "estadoConverter")
public class EstadoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoDAO estadoDAO;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String str) throws ConverterException {
		if (str == null || str.isEmpty()) {
			return null;
		}

		Estado estado = this.estadoDAO.find(Long.parseLong(str));

		return estado;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object obj) throws ConverterException {
		if (obj == null) {
			return "";
		}

		if (!(obj instanceof Estado)) {
			return "";
		}

		Estado estado = (Estado) obj;

		return estado.getCodigo().toString();
	}
}