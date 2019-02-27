package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class LargoDecimalException extends Exception {
	
	public LargoDecimalException () {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe ingresar un numero que no contenga mas de dos decimales","Error"));
	}
}
