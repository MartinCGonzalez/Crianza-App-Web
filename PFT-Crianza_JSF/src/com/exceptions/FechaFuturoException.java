package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class FechaFuturoException extends Exception {
	
	public FechaFuturoException () {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No puede seleccionar fecha a futuro!","Error"));
}

}
