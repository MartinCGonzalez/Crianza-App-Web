package com.dtos;

import java.io.Serializable;

public class EnfermedadDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long idEnfermedad;
	private String nombre;
	private Long gradoGravedad;
	
	public EnfermedadDTO() {
	}

	public EnfermedadDTO(Long idEnfermedad, String nombre, Long gradoGravedad) {
		super();
		this.idEnfermedad = idEnfermedad;
		this.nombre = nombre;
		this.gradoGravedad = gradoGravedad;
	}

	public Long getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(Long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public Long getGradoGravedad() {
		return gradoGravedad;
	}

	public void setGradoGravedad(Long gradoGravedad) {
		this.gradoGravedad = gradoGravedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

}
