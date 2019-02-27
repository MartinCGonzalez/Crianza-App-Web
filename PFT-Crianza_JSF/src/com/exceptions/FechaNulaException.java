package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class FechaNulaException extends Exception {
	
	public FechaNulaException () {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"La Fecha no puede ser Nula","Error"));
	}
}