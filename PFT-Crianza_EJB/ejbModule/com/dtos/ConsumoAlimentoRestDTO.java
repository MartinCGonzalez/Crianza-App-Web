package com.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class ConsumoAlimentoRestDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long idConsumoAlimento;
	private long idTernero;
	private long idAlimento;
	private BigDecimal cantidad;
	private String fecha;
	
	private Long nroCaravana;
	private String alimento;
	private String unidad;
	
	public ConsumoAlimentoRestDTO() {
		super();
	}

	public ConsumoAlimentoRestDTO(long idTernero, long idAlimento, BigDecimal cantidad, String fecha) {
		super();
		this.idTernero = idTernero;
		this.idAlimento = idAlimento;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}
	
	public ConsumoAlimentoRestDTO(long idConsumoAlimento, long idTernero, Long nroCaravana, String fecha, String alimento, BigDecimal cantidad,
			String unidad) {
		super();
		this.idConsumoAlimento = idConsumoAlimento;
		this.idTernero = idTernero;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.nroCaravana = nroCaravana;
		this.alimento = alimento;
		this.unidad = unidad;
	}

	public long getIdConsumoAlimento() {
		return idConsumoAlimento;
	}



	public void setIdConsumoAlimento(long idConsumoAlimento) {
		this.idConsumoAlimento = idConsumoAlimento;
	}



	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public long getIdTernero() {
		return idTernero;
	}

	public void setIdTernero(long idTernero) {
		this.idTernero = idTernero;
	}

	public Long getNroCaravana() {
		return nroCaravana;
	}

	public void setNroCaravana(Long nroCaravana) {
		this.nroCaravana = nroCaravana;
	}

	public String getAlimento() {
		return alimento;
	}

	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}



	@Override
	public String toString() {
		return "ConsumoAlimentoRestDTO [idTernero=" + idTernero + ", idAlimento=" + idAlimento + ", cantidad="
				+ cantidad + ", fecha=" + fecha + ", nroCaravana=" + nroCaravana + ", alimento=" + alimento
				+ ", unidad=" + unidad + "]";
	}
	
	

}
