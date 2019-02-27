package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class ObservacionException extends Exception {
	
	public ObservacionException () {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No igresar mas de 250 caracteres!","Error"));
	}
}
