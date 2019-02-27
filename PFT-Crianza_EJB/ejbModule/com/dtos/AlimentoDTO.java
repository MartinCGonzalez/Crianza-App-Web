package com.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import com.entities.Unidad;

public class AlimentoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idAlimento;
	private BigDecimal cantidad;
	private BigDecimal costoUnidad;
	private String nombre;
	private Unidad unidad;
	private String tipoUnidad;
	

	public AlimentoDTO() {
		super();
		}

	public AlimentoDTO(Long idAlimento, BigDecimal cantidad, BigDecimal costoUnidad, String nombre, Unidad unidad) {
		super();
		this.idAlimento = idAlimento;
		this.cantidad = cantidad;
		this.costoUnidad = costoUnidad;
		this.nombre = nombre;
		this.unidad = unidad;
	}
	
	
	
	public AlimentoDTO(Long idAlimento, String nombre, BigDecimal cantidad, BigDecimal costoUnidad, Unidad unidad) {
		super();
		this.idAlimento = idAlimento;
		this.cantidad = cantidad;
		this.costoUnidad = costoUnidad;
		this.nombre = nombre;
		this.unidad = unidad;
	}
	
	
	
	public AlimentoDTO(Long idAlimento, String nombre, BigDecimal costoUnidad, BigDecimal cantidad, String tipoUnidad) {
		super();
		this.idAlimento = idAlimento;
		this.cantidad = cantidad;
		this.costoUnidad = costoUnidad;
		this.nombre = nombre;
		this.tipoUnidad = tipoUnidad;
	}
	
    public AlimentoDTO(String nombre,BigDecimal costoUnidad , BigDecimal cantidad) {
        super();
        this.nombre = nombre;
        this.costoUnidad = costoUnidad;
        this.cantidad = cantidad;
    }

	public AlimentoDTO(String nombre) {
		this.nombre = nombre;
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
		return "AlimentoDTO [idAlimento=" + idAlimento + ", cantidad=" + cantidad + ", costoUnidad=" + costoUnidad
				+ ", nombre=" + nombre + ", unidad=" + unidad + "]";
	}

	
	
}
