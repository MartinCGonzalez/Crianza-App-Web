package com.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import com.entities.Unidad;

public class AlimentoRestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idAlimento;
	private String nombre;
	private BigDecimal costoUnidad;
	private BigDecimal cantidad;
	private Unidad unidad;
	private String tipoUnidad;

	
	public AlimentoRestDTO() {
		super();
	}
	
	public AlimentoRestDTO(Long idAlimento, String nombre, BigDecimal costoUnidad, BigDecimal cantidad, Unidad unidad) {
		super();
		this.idAlimento = idAlimento;
		this.nombre = nombre;
		this.costoUnidad = costoUnidad;
		this.cantidad = cantidad;
		this.unidad = unidad;
	}

	public AlimentoRestDTO(Long idAlimento, String nombre) {
		super();
		this.idAlimento = idAlimento;
		this.nombre = nombre;
	}
	

	public AlimentoRestDTO(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	


	public AlimentoRestDTO(Long idAlimento, String nombre, BigDecimal costoUnidad, BigDecimal cantidad,
			String tipoUnidad) {
		super();
		this.idAlimento = idAlimento;
		this.nombre = nombre;
		this.costoUnidad = costoUnidad;
		this.cantidad = cantidad;
		this.tipoUnidad = tipoUnidad;
	}

	public Long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public BigDecimal getCostoUnidad() {
		return costoUnidad;
	}

	public void setCostoUnidad(BigDecimal costoUnidad) {
		this.costoUnidad = costoUnidad;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	
	
	
	public String getTipoUnidad() {
		return tipoUnidad;
	}

	public void setTipoUnidad(String tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}

	@Override
	public String toString() {
		return "AlimentoRestDTO [idAlimento=" + idAlimento + ", nombre=" + nombre + ", costoUnidad=" + costoUnidad
				+ ", cantidad=" + cantidad + ", unidad=" + unidad + "]";
	}
	


}
