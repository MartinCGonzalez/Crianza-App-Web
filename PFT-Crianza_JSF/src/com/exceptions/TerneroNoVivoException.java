package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class TerneroNoVivoException extends Exception {
	
	public TerneroNoVivoException () {			
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"El ternero no está vivo!","Error"));
	}
}
