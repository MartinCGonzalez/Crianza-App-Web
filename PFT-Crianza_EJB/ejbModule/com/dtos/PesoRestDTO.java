package com.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class PesoRestDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idPeso;
	private Long idTernero;
	private String fecha;
	private String tipoRegistro;
	private BigDecimal ganancia;
	private BigDecimal peso;

	
	public PesoRestDTO() {
		super();
	}

	public PesoRestDTO(Long idPeso, String fecha, BigDecimal peso, String tipoRegistro, Long idTernero) {
		super();
		this.idPeso = idPeso;
		this.fecha = fecha;
		this.peso = peso;
		this.tipoRegistro = tipoRegistro;
		this.idTernero = idTernero;
	}
	
	

	public PesoRestDTO(Long idPeso, Long idTernero, String fecha, BigDecimal peso, BigDecimal ganancia, String tipoRegistro ) {
		super();
		this.idPeso = idPeso;
		this.idTernero = idTernero;
		this.fecha = fecha;
		this.peso = peso;
		this.ganancia = ganancia;
		this.tipoRegistro = tipoRegistro;
	}

	public BigDecimal getGanancia() {
		return ganancia;
	}

	public void setGanancia(BigDecimal ganancia) {
		this.ganancia = ganancia;
	}

	public Long getIdPeso() {
		return idPeso;
	}

	public void setIdPeso(Long idPeso) {
		this.idPeso = idPeso;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Long getIdTernero() {
		return idTernero;
	}

	public void setIdTernero(Long idTernero) {
		this.idTernero = idTernero;
	}

	@Override
	public String toString() {
		return "PesoRestDTO [idPeso=" + idPeso + ", fecha=" + fecha + ", peso=" + peso + ", tipoRegistro="
				+ tipoRegistro + ", idTernero=" + idTernero + "]";
	}
	
	
	
	

}
