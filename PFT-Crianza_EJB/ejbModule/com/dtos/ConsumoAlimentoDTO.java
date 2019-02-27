package com.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConsumoAlimentoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long idConsumoAlimento;
	private Long idTernero;
	private Long nroCaravana;
	private Date fecha;
	private String alimento;
	private BigDecimal cantidad;
	private String unidad;
	
	public ConsumoAlimentoDTO() {
		super();
	}

	public ConsumoAlimentoDTO(Long idTernero, Long nroCaravana, Date fecha, String alimento, BigDecimal cantidad,
			String unidad) {
		super();
		this.idTernero = idTernero;
		this.nroCaravana = nroCaravana;
		this.fecha = fecha;
		this.alimento = alimento;
		this.cantidad = cantidad;
		this.unidad = unidad;
	}
	
	
	

	public ConsumoAlimentoDTO(long idConsumoAlimento, Long idTernero, Long nroCaravana, Date fecha, String alimento,
			BigDecimal cantidad, String unidad) {
		super();
		this.idConsumoAlimento = idConsumoAlimento;
		this.idTernero = idTernero;
		this.nroCaravana = nroCaravana;
		this.fecha = fecha;
		this.alimento = alimento;
		this.cantidad = cantidad;
		this.unidad = unidad;
	}

	public long getIdConsumoAlimento() {
		return idConsumoAlimento;
	}

	public void setIdConsumoAlimento(long idConsumoAlimento) {
		this.idConsumoAlimento = idConsumoAlimento;
	}

	public Long getIdTernero() {
		return idTernero;
	}

	public void setIdTernero(Long idTernero) {
		this.idTernero = idTernero;
	}

	public Long getNroCaravana() {
		return nroCaravana;
	}

	public void setNroCaravana(Long nroCaravana) {
		this.nroCaravana = nroCaravana;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getAlimento() {
		return alimento;
	}

	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	@Override
	public String toString() {
		return "ConsumoAlimentoDTO [idTernero=" + idTernero + ", nroCaravana=" + nroCaravana + ", fecha=" + fecha
				+ ", alimento=" + alimento + ", cantidad=" + cantidad + ", unidad=" + unidad + "]";
	}
	
}
