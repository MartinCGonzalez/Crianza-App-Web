package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class LargoNumeroException extends Exception {

	public LargoNumeroException () {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"El largo del numero ingresado excede lo permitido","Error"));
	}
}
