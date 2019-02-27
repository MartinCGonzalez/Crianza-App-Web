package com.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class RegistroPesoVacioException extends Exception  {
	
	public RegistroPesoVacioException () {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe registrar pesos antes para generar informe de ganancia de peso","Error"));
	}
}
