package com.dtos;

import java.io.Serializable;
import java.util.List;

import com.entities.Alimento;
import com.entities.ConsumoAlimento;

public class UnidadDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private long idUnidad;
	private String unidad;
	private List<Alimento> alimentos;
	private List<ConsumoAlimento> consumoAlimentos;

	public UnidadDTO() {
		super();
	}
	
	public UnidadDTO(long idUnidad, String unidad, List<Alimento> alimentos, List<ConsumoAlimento> consumoAlimentos) {
		super();
		this.idUnidad = idUnidad;
		this.unidad = unidad;
		this.alimentos = alimentos;
		this.consumoAlimentos = consumoAlimentos;
	}
	
	public UnidadDTO(Long idUnidad, String unidad) {
		super();
		this.idUnidad = idUnidad;
		this.unidad = unidad;
	}
	
	public long getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(long idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public List<ConsumoAlimento> getConsumoAlimentos() {
		return consumoAlimentos;
	}

	public void setConsumoAlimentos(List<ConsumoAlimento> consumoAlimentos) {
		this.consumoAlimentos = consumoAlimentos;
	}

}
