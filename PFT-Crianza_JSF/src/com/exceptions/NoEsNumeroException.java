package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class NoEsNumeroException extends Exception {

	public NoEsNumeroException() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"La cantidad debe de ser un numero!","Error"));
	}
}
