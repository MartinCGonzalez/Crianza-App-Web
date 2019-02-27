package com.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import com.dtos.TerneroDTO;
import com.entities.Ternero;
import com.enums.TipoParto;
import com.enums.TipoRaza;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.TernerosBean;
import com.validaciones.ValidacionDatos;

@ManagedBean
@SessionScoped
public class TerneroMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public TerneroMB() {
		if(ternero == null) {
			ternero = new TerneroDTO();
		}
	}
	
	@EJB
	private TernerosBean ternerosBean;
	
	private TerneroDTO ternero = new TerneroDTO();
	
	private LinkedList<TerneroDTO> terneros = new LinkedList<TerneroDTO>();
	
	private Long idTernero;
	
	private Date fechaNac;
	
	private BigDecimal pesoNac;
	
	private Long nroCaravana;
	
	private String parto;
	
	private String raza;
	
	private Long idGuachera;
	
	private Long idMadre;
	
	private Long idPadre;
	

	   public String altaTernero() throws NamingException, ServiciosException {
	    	
	    	String mostrar = null;
	    	
	    	boolean alta = false;
	    	
			long nroCaravana = ternero.getNroCaravana();
						
			BigDecimal pesoNac = ternero.getPesoNac();
			
			Date fechaNac = (Date) ternero.getFechaNac();
			
			String parto = ternero.getParto();
			
			String raza = ternero.getRaza();
			
			long idGuachera = ternero.getIdGuachera();
			
			long idMadre = ternero.getIdMadre();
			
			long idPadre = ternero.getIdPadre();
			

			// NUEVOS ATRIBUTOS DE ALIMENTO
			try {
				
				if(ValidacionDatos.validarTernero(nroCaravana, pesoNac, fechaNac)) {
				Ternero t = new Ternero(pesoNac, fechaNac, nroCaravana, parto, raza, idGuachera, idMadre, idPadre);	
				ternerosBean.alta(t);
				alta = true;

				if (alta) {
					FacesMessage msg = new FacesMessage("El Ternero se ha ingresado con éxito");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					return mostrar;
					
				} else {
					limpiarFormulario();
					mostrar = "ingresoTernero";
					return mostrar;

				}
			}

			} catch (Exception e) {
				FacesMessage msg = new FacesMessage("No se pudo agregar el ternero!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				e.printStackTrace();
			}
			return mostrar;
	    	
	    }
	

	// Cargo los campos del combo raza
	public TipoRaza[] getRazas() {
		return TipoRaza.values();
	}
	
	// Cargo los campos del combo parto
	public TipoParto[] getPartos() {
		return TipoParto.values();
	}
	  
	public void limpiarFormulario() {
			this.ternero.setPesoNac(null);
			this.ternero.setFechaNac(null);
			this.ternero.setNroCaravana(null);
			this.ternero.setNroCaravana(null);
			this.ternero.setParto(null);
			this.ternero.setRaza(null);
			this.ternero.setIdGuachera(null);
			this.ternero.setIdMadre(null);
			this.ternero.setIdPadre(null);
	}
	
	public List<Ternero> getAllTerneros() throws MyException, ServiciosException {

		List<Ternero> todosTerneros = new LinkedList<Ternero>();
		todosTerneros = ternerosBean.obtenerTodos();

		return todosTerneros;

	}

	public TernerosBean getTernerosBean() {
		return ternerosBean;
	}

	public void setTernerosBean(TernerosBean ternerosBean) {
		this.ternerosBean = ternerosBean;
	}

	public TerneroDTO getTernero() {
		return ternero;
	}

	public void setTernero(TerneroDTO ternero) {
		this.ternero = ternero;
	}

	public LinkedList<TerneroDTO> getTerneros() {
		return terneros;
	}

	public void setTerneros(LinkedList<TerneroDTO> terneros) {
		this.terneros = terneros;
	}

	public Long getIdTernero() {
		return idTernero;
	}

	public void setIdTernero(Long idTernero) {
		this.idTernero = idTernero;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public BigDecimal getPesoNac() {
		return pesoNac;
	}

	public void setPesoNac(BigDecimal pesoNac) {
		this.pesoNac = pesoNac;
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


	
	

}
