package com.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TerneroRestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idTernero;
	private String causaMuerte;
	private Date fechaBaja;
	private Date fechaMuerte;
//	private Date fechaNac;
	private String fechaNac;
	private String motivoBaja;
	private Long nroCaravana;
	private String parto;
	private BigDecimal pesoNac;
	private String raza;
	private Long idGuachera;
	private Long idMadre;
	private Long idPadre;
	
	public TerneroRestDTO() {
		super();
	}

	public TerneroRestDTO(Long nroCaravana, Long idGuachera, Long idMadre, Long idPadre, BigDecimal pesoNac, String fechaNac , String parto, String raza) {
		super();
		this.fechaNac = fechaNac;
		this.nroCaravana = nroCaravana;
		this.parto = parto;
		this.pesoNac = pesoNac;
		this.raza = raza;
		this.idGuachera = idGuachera;
		this.idMadre = idMadre;
		this.idPadre = idPadre;
	}



	public TerneroRestDTO(Long idTernero, Long nroCaravana, Long idGuachera) {
		super();
		this.idTernero = idTernero;
		this.nroCaravana = nroCaravana;
		this.idGuachera = idGuachera;
	}

	public Long getIdTernero() {
		return idTernero;
	}

	public void setIdTernero(Long idTernero) {
		this.idTernero = idTernero;
	}

	public String getCausaMuerte() {
		return causaMuerte;
	}

	public void setCausaMuerte(String causaMuerte) {
		this.causaMuerte = causaMuerte;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaMuerte() {
		return fechaMuerte;
	}

	public void setFechaMuerte(Date fechaMuerte) {
		this.fechaMuerte = fechaMuerte;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getMotivoBaja() {
		return motivoBaja;
	}

	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}

	public Long getNroCaravana() {
		return nroCaravana;
	}

	public void setNroCaravana(Long nroCaravana) {
		this.nroCaravana = nroCaravana;
	}

	public String getParto() {
		return parto;
	}

	public void setParto(String parto) {
		this.parto = parto;
	}

	public BigDecimal getPesoNac() {
		return pesoNac;
	}

	public void setPesoNac(BigDecimal pesoNac) {
		this.pesoNac = pesoNac;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Long getIdGuachera() {
		return idGuachera;
	}

	public void setIdGuachera(Long idGuachera) {
		this.idGuachera = idGuachera;
	}

	public Long getIdMadre() {
		return idMadre;
	}

	public void setIdMadre(Long idMadre) {
		this.idMadre = idMadre;
	}

	public Long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}

	@Override
	public String toString() {
		return "TerneroRestDTO [idTernero=" + idTernero + ", causaMuerte=" + causaMuerte + ", fechaBaja=" + fechaBaja
				+ ", fechaMuerte=" + fechaMuerte + ", fechaNac=" + fechaNac + ", motivoBaja=" + motivoBaja
				+ ", nroCaravana=" + nroCaravana + ", parto=" + parto + ", pesoNac=" + pesoNac + ", raza=" + raza
				+ ", idGuachera=" + idGuachera + ", idMadre=" + idMadre + ", idPadre=" + idPadre + "]";
	}
	

}
