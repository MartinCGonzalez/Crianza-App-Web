package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class NumeroNegativoException extends Exception {
	
	public NumeroNegativoException () {			
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"El valor ingresado no puede ser negativo o igual a 0","Error"));
	}
}