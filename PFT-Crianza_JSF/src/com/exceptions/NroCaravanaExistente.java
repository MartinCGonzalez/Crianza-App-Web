package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class NroCaravanaExistente extends Exception {
	
	public NroCaravanaExistente () {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Numero de Caravana ya ingresado!","Error"));
	}
}
