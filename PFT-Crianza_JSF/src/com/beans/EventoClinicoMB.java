package com.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

import com.dtos.EventoClinicoDTO;
import com.entities.Enfermedad;
import com.entities.EventoClinico;
import com.entities.Ternero;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.EnfermedadBean;
import com.servicios.EventoClinicoBean;
import com.servicios.TernerosBean;
import com.validaciones.ValidacionDatos;

@ManagedBean
@SessionScoped
public class EventoClinicoMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public EventoClinicoMB() {
		if(eventoClinico == null) {
			eventoClinico = new EventoClinicoDTO();
		}
	}

	@EJB
	private TernerosBean ternerosBean;
	
	@EJB
	private EnfermedadBean enfermedadBean;
	
	@EJB
	private EventoClinicoBean eventoClinicoBean;
	
	private EventoClinicoDTO eventoClinico = new EventoClinicoDTO();
	
	private Enfermedad enfermedadSeleccionada;
	
	private Ternero terneroSeleccionado;
	
	private Long idTernero1;

	private String observacion;
		
	public void onRowSelect(SelectEvent event_) {
	}

	public String altaEventoClinico() throws ServiciosException {
		
		String mostrar = null;
		
		Long idTernero = terneroSeleccionado.getIdTernero();
		
		Long idEnfermedad = enfermedadSeleccionada.getIdEnfermedad();
		
		Enfermedad enfermedad = enfermedadBean.obtenerEnfermedad(idEnfermedad);
		
		Date fechaDesde = eventoClinico.getFechaDesde();
		
		Date fechaHasta = eventoClinico.getFechaHasta();
		
		String observacion = eventoClinico.getObservacion();
		
		Ternero ternero = ternerosBean.obtenerTernero(idTernero);
		
		if (idTernero != null && idEnfermedad != null && fechaDesde != null && fechaHasta == null) {
			try {
				if (ValidacionDatos.validarEventoClinico(fechaDesde, observacion)) {
					
					Date fechaNac = ternero.getFechaNac();
					Date fechaDesde1 = fechaDesde;
					
					long diasVida1 = ((int)((fechaDesde1.getTime()/(24*60*60*1000)) -
				    		(int)(fechaNac.getTime()/(24*60*60*1000))));
					
					Long diasVida = diasVida1;

					EventoClinico ec = new EventoClinico(fechaDesde, enfermedad, ternero, observacion, diasVida);

					eventoClinicoBean.guardarEventoClinico(ec);

					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Evento Registrado", "CONFIRMACION | SOLICITUD COMPLETADA"));
				}

			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"ADVERTENCIA | SOLICITUD NO COMPLETADA", "CONFIRMACION | SOLICITUD NO COMPLETADA"));
				e.printStackTrace();
			}
		}
		
		if (ternero != null && enfermedad != null && fechaDesde != null && fechaHasta != null) {
			try {
				if (ValidacionDatos.validarEventoClinico2(fechaDesde, fechaHasta, observacion)) {
					
					Date fechaNac = ternero.getFechaNac();
					Date fechaDesde1 = fechaDesde;
					
					long diasVida1 = ((int)((fechaDesde1.getTime()/(24*60*60*1000)) -
				    		(int)(fechaNac.getTime()/(24*60*60*1000))));
					
					Long diasVida = diasVida1;


					EventoClinico ec = new EventoClinico(fechaDesde, enfermedad, ternero, fechaHasta, observacion, diasVida);
					try {

						if (fechaHasta.before(fechaDesde)) {					
							FacesMessage msg = new FacesMessage("La fecha Desde no puede ser menor a la fecha Hasta");
							FacesContext.getCurrentInstance().addMessage(null, msg);
						}
					} catch (Exception e) {
						FacesMessage msg = new FacesMessage("ADVERTENCIA | SOLICITUD NO COMPLETADA");
						FacesContext.getCurrentInstance().addMessage(null, msg);

						e.printStackTrace();
					}
					eventoClinicoBean.guardarEventoClinico(ec);
					FacesMessage msg = new FacesMessage("Evento registrado");
					FacesContext.getCurrentInstance().addMessage(null, msg);


				}
			} catch (Exception e) {
				FacesMessage msg = new FacesMessage("ADVERTENCIA | SOLICITUD NO COMPLETADA");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				e.printStackTrace();
			}
		}

		
						
		return mostrar;

	}
		
	public List<Enfermedad> getAllEnfermedades() throws MyException {

		List<Enfermedad> enfermedades = new LinkedList<Enfermedad>();
		enfermedades = enfermedadBean.obtenerTodos();

		return enfermedades;

	}

	public List<EventoClinico> getAllEventoClinico() throws MyException {

		List<EventoClinico> eventosClinicos = new LinkedList<EventoClinico>();
		eventosClinicos = eventoClinicoBean.obtenerTodos();

		return eventosClinicos;

	}
	
	public void limpiarFormulario() {
		this.eventoClinico.setTernero(null);
		this.eventoClinico.setFechaDesde(null);
		this.eventoClinico.setFechaHasta(null);
		this.eventoClinico.setObservacion(null);
	}
	
	//Redirige la pantalla a ingreso de evento clinico
	public String ingresoEventoClinico() {
		return "ingresoEventoClinico";
	}


	public TernerosBean getTernerosBean() {
		return ternerosBean;
	}


	public void setTernerosBean(TernerosBean ternerosBean) {
		this.ternerosBean = ternerosBean;
	}


	public EnfermedadBean getEnfermedadBean() {
		return enfermedadBean;
	}


	public void setEnfermedadBean(EnfermedadBean enfermedadBean) {
		this.enfermedadBean = enfermedadBean;
	}


	public EventoClinicoBean getEventoClinicoBean() {
		return eventoClinicoBean;
	}


	public void setEventoClinicoBean(EventoClinicoBean eventoClinicoBean) {
		this.eventoClinicoBean = eventoClinicoBean;
	}


	public EventoClinicoDTO getEventoClinico() {
		return eventoClinico;
	}


	public void setEventoClinico(EventoClinicoDTO eventoClinico) {
		this.eventoClinico = eventoClinico;
	}


	public Enfermedad getEnfermedadSeleccionada() {
		return enfermedadSeleccionada;
	}


	public void setEnfermedadSeleccionada(Enfermedad enfermedadSeleccionada) {
		this.enfermedadSeleccionada = enfermedadSeleccionada;
	}

	public Long getIdTernero1() {
		return idTernero1;
	}

	public void setIdTernero1(Long idTernero1) {
		this.idTernero1 = idTernero1;
	}

	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	

	public Ternero getTerneroSeleccionado() {
		return terneroSeleccionado;
	}

	public void setTerneroSeleccionado(Ternero terneroSeleccionado) {
		this.terneroSeleccionado = terneroSeleccionado;
	}
	
	

	
	
}
