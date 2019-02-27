package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class EnfermedadException extends Exception {

	public EnfermedadException () {			
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"C�digo de enfermedad incorrecto!","Error"));
		}
}
