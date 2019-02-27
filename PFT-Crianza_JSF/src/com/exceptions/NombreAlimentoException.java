package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")

public class NombreAlimentoException extends Exception {

	public NombreAlimentoException() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"El nombre del alimento es incorrecto!","Error"));
	}
}
