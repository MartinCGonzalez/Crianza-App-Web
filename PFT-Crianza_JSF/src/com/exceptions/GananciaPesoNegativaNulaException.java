package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class GananciaPesoNegativaNulaException extends Exception {

	public GananciaPesoNegativaNulaException () {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"El ternero no muestra ganancia de peso frente a registro anterior, se recomienda dar una revision inmediata","Error"));
	}
}
