package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class StockInsuficienteException extends Exception {
	public StockInsuficienteException () {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No hay suficiente stock del alimento seleccionado","Error"));
	}
}
