package com.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import com.entities.Unidad;

public class AlimentoPostDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idAlimento;
	private String nombre;
	private BigDecimal cantidad;
	private BigDecimal costoUnidad;
	private Long unidad;
	
	
	
	public AlimentoPostDTO() {
		super();
	}
	
	

	public AlimentoPostDTO(String nombre, BigDecimal cantidad, BigDecimal costoUnidad) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.costoUnidad = costoUnidad;
	}



	public AlimentoPostDTO(Long idAlimento, BigDecimal cantidad, BigDecimal costoUnidad, String nombre, Long unidad) {
		super();
		this.idAlimento = idAlimento;
		this.cantidad = cantidad;
		this.costoUnidad = costoUnidad;
		this.nombre = nombre;
		this.unidad = unidad;
	}

	public Long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCostoUnidad() {
		return costoUnidad;
	}

	public void setCostoUnidad(BigDecimal costoUnidad) {
		this.costoUnidad = costoUnidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getUnidad() {
		return unidad;
	}

	public void setUnidad(Long unidad) {
		this.unidad = unidad;
	}

	


}
