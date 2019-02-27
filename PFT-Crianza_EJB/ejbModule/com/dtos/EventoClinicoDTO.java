package com.dtos;

import java.io.Serializable;
import java.util.Date;

import com.entities.Enfermedad;
import com.entities.Ternero;

public class EventoClinicoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idEventoClinico;
	private Date fechaDesde;
	private Date fechaHasta;
	private String observacion;
	private Enfermedad enfermedad;
	private Ternero ternero;
	
	private Long diasVida;
	private Long idTernero;
	private Date fechaNacimiento;
	private String nombreEnfermedad;
	private Long gravedad;

		
	public EventoClinicoDTO() {
		
	}

	public EventoClinicoDTO(Date fechaDesde, Enfermedad enfermedad, Ternero ternero) {
		super();
		this.fechaDesde = fechaDesde;
		this.enfermedad = enfermedad;
		this.ternero = ternero;
	}

	public EventoClinicoDTO(Date fechaDesde, Date fechaHasta, String observacion, Enfermedad enfermedad,
			Ternero ternero) {
		super();
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.observacion = observacion;
		this.enfermedad = enfermedad;
		this.ternero = ternero;
	}
	
	public EventoClinicoDTO(Long idEventoClinico, Long idTernero, Date fechaNacimiento, Long diasVida, Date fechaDesde,
			Date fechaHasta, String nombreEnfermedad, Long gravedad) {
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

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Ternero getTernero() {
		return ternero;
	}

	public void setTernero(Ternero ternero) {
		this.ternero = ternero;
	}

	public Long getDiasVida() {
		return diasVida;
	}

	public void setDiasVida(Long diasVida) {
		this.diasVida = diasVida;
	}

	public Long getIdTernero() {
		return idTernero;
	}

	public void setIdTernero(Long idTernero) {
		this.idTernero = idTernero;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
