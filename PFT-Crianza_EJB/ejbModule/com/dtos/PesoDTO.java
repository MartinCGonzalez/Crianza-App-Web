package com.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.entities.Ternero;

public class PesoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idPeso;
	private Date fecha;
	private BigDecimal peso;
	private String tipoRegistro;
	private Ternero ternero;
	private Long idTernero;
	private BigDecimal ganancia;
	
	public PesoDTO() {
		
	}
	
	public PesoDTO(Date fecha, BigDecimal peso, String tipoRegistro, Ternero ternero,
			Long idTernero) {
		super();
		this.fecha = fecha;
		this.peso = peso;
		this.tipoRegistro = tipoRegistro;
		this.ternero = ternero;
		this.idTernero = idTernero;
	}
	
	public PesoDTO(Date fecha, BigDecimal peso, String tipoRegistro, Long idTernero,
			BigDecimal ganancia) {
		super();
		this.fecha = fecha;
		this.peso = peso;
		this.tipoRegistro = tipoRegistro;
		this.idTernero = idTernero;
		this.ganancia = ganancia;
	}
	
	
	public PesoDTO(Long idPeso, Long idTernero, Date fecha, BigDecimal peso, BigDecimal ganancia, String tipoRegistro) {
		super();
		this.idPeso = idPeso;
		this.idTernero = idTernero;
		this.fecha = fecha;
		this.peso = peso;
		this.ganancia = ganancia;
		this.tipoRegistro = tipoRegistro;

	}

	public Long getIdPeso() {
		return idPeso;
	}

	public void setIdPeso(Long idPeso) {
		this.idPeso = idPeso;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
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

	public Ternero getTernero() {
		return ternero;
	}

	public void setTernero(Ternero ternero) {
		this.ternero = ternero;
	}

	public Long getIdTernero() {
		return idTernero;
	}

	public void setIdTernero(Long idTernero) {
		this.idTernero = idTernero;
	}

	public BigDecimal getGanancia() {
		return ganancia;
	}

	public void setGanancia(BigDecimal ganancia) {
		this.ganancia = ganancia;
	}
	
		
}
