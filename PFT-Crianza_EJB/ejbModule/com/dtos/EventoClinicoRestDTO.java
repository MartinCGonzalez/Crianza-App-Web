package com.dtos;

import java.util.Date;

public class EventoClinicoRestDTO {
	
	private Long idEventoClinico;
	private Long idTernero;
	private Long idEnfermedad;
	private String fechaDesde;
	private String fechaHasta;
	private String observacion;
	
	private String fechaNacimiento;
	private Long diasVida;
	private String nombreEnfermedad;
	private Long gravedad;

	
	public EventoClinicoRestDTO() {
		super();
	}

	public EventoClinicoRestDTO(Long idTernero, Long idEnfermedad, String fechaDesde, String fechaHasta,
			String observacion) {
		super();
		this.idTernero = idTernero;
		this.idEnfermedad = idEnfermedad;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.observacion = observacion;
	}
	
	public EventoClinicoRestDTO(Long idEventoClinico, Long idTernero, String fechaNacimiento, Long diasVida, String fechaDesde,
			String fechaHasta, String nombreEnfermedad, Long gravedad) {
		super();
		this.idEventoClinico = idEventoClinico;
		this.idTernero = idTernero;
		this.fechaNacimiento = fechaNacimiento;
		this.diasVida = diasVida;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.nombreEnfermedad = nombreEnfermedad;
		this.gravedad = gravedad;
	}


	public Long getIdEventoClinico() {
		return idEventoClinico;
	}

	public void setIdEventoClinico(Long idEventoClinico) {
		this.idEventoClinico = idEventoClinico;
	}

	public Long getIdTernero() {
		return idTernero;
	}

	public void setIdTernero(Long idTernero) {
		this.idTernero = idTernero;
	}

	public Long getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(Long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getDiasVida() {
		return diasVida;
	}

	public void setDiasVida(Long diasVida) {
		this.diasVida = diasVida;
	}

	public String getNombreEnfermedad() {
		return nombreEnfermedad;
	}

	public void setNombreEnfermedad(String nombreEnfermedad) {
		this.nombreEnfermedad = nombreEnfermedad;
	}

	public Long getGravedad() {
		return gravedad;
	}

	public void setGravedad(Long gravedad) {
		this.gravedad = gravedad;
	}	
	
}
