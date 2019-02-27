package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class IdExistente extends Exception{
	
	public IdExistente () {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alimento ya ingresado, Prueba Actualizarlo!","Error"));

	}
}

