package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class FormatoFechaNoValido extends Exception {
	
	public FormatoFechaNoValido () {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"El formato de la fecha debe ser DD/MM/YYYY","Error"));
	}

}
