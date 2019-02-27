package com.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.entities.ConsumoAlimento;
import com.entities.ConsumoMedicamento;
import com.entities.EventoClinico;
import com.entities.Peso;

public class TerneroDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idTernero;
	private String causaMuerte;
	private Date fechaBaja;
	private Date fechaMuerte;
	private Date fechaNac;
	private String motivoBaja;
	private Long nroCaravana;
	private String parto;
	private BigDecimal pesoNac;
	private String raza;
	private List<ConsumoAlimento> consumoAlimentos;
	private List<ConsumoMedicamento> consumoMedicamentos;
	private List<EventoClinico> eventoClinicos;
	private List<Peso> listaRegPeso;
	private Long idGuachera;
	private Long idMadre;
	private Long idPadre;

	public TerneroDTO() {
		super();
		
	}
	
	public TerneroDTO(Long diasVida) {
		super();
	}

	public TerneroDTO(Date fechaNac, Long nroCaravana, String parto, BigDecimal pesoNac, String raza, Long guachera,
			Long madre, Long padre) {
		super();
		this.fechaNac = fechaNac;
		this.nroCaravana = nroCaravana;
		this.parto = parto;
		this.pesoNac = pesoNac;
		this.raza = raza;
		this.idGuachera = guachera;
		this.idMadre = madre;
		this.idPadre = padre;
	}
	

	public TerneroDTO(Long idTernero, Long nroCaravana, Long idGuachera) {
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

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
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

	public List<ConsumoAlimento> getConsumoAlimentos() {
		return consumoAlimentos;
	}

	public void setConsumoAlimentos(List<ConsumoAlimento> consumoAlimentos) {
		this.consumoAlimentos = consumoAlimentos;
	}

	public List<ConsumoMedicamento> getConsumoMedicamentos() {
		return consumoMedicamentos;
	}

	public void setConsumoMedicamentos(List<ConsumoMedicamento> consumoMedicamentos) {
		this.consumoMedicamentos = consumoMedicamentos;
	}

	public List<EventoClinico> getEventoClinicos() {
		return eventoClinicos;
	}

	public void setEventoClinicos(List<EventoClinico> eventoClinicos) {
		this.eventoClinicos = eventoClinicos;
	}

	public List<Peso> getListaRegPeso() {
		return listaRegPeso;
	}

	public void setListaRegPeso(List<Peso> listaRegPeso) {
		this.listaRegPeso = listaRegPeso;
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


	
	
	

	

}
