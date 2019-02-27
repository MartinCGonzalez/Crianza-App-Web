package com.ws;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dtos.EnfermedadDTO;
import com.dtos.EventoClinicoDTO;
import com.dtos.EventoClinicoRestDTO;
import com.entities.Enfermedad;
import com.entities.EventoClinico;
import com.entities.Ternero;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.EnfermedadBeanRemote;
import com.servicios.EventoClinicoBeanRemote;
import com.servicios.TernerosBean;
import com.validaciones.ValidacionDatos;

@Stateless
@Path("/eventoClinico")
@Produces("text/plain")
public class EventoClinicoRest implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private TernerosBean ternerosBean;

	@EJB
	private EnfermedadBeanRemote enfermedadBean;

	@EJB
	private EventoClinicoBeanRemote eventoClinicoBean;

	// Ingresar Evento Clinico
	@POST
	@Path("/ingresoEventoClinico")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String altaEventoClinico(EventoClinicoRestDTO eveCli)
			throws MyException, ParseException, ServiciosException, SQLException {

		Long idTernero = eveCli.getIdTernero();

		Long idEnfermedad = eveCli.getIdEnfermedad();

		Enfermedad enfermedad = enfermedadBean.obtenerEnfermedad(idEnfermedad);

		String fechaD = eveCli.getFechaDesde();

		String fechaH = eveCli.getFechaHasta();

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Date fechaDesde = format.parse(fechaD);


		String observacion = eveCli.getObservacion();

		Ternero ternero = ternerosBean.obtenerTernero(idTernero);

		if (idTernero != null && idEnfermedad != null && fechaDesde != null && fechaH == null) {

			try {

				Date fechaNac = ternero.getFechaNac();
				
				Date fechaDesde1 = fechaDesde;

				long diasVida1 = ((int) ((fechaDesde1.getTime() / (24 * 60 * 60 * 1000))
						- (int) (fechaNac.getTime() / (24 * 60 * 60 * 1000))));

				Long diasVida = diasVida1;

				EventoClinico ec = new EventoClinico(fechaDesde, enfermedad, ternero, observacion, diasVida);

				eventoClinicoBean.guardarEventoClinico(ec);

			} catch (Exception e) {
				e.printStackTrace();
				return "No se pudo completar el registro!";
			}
			return "Registro Completado";

		}

		if (ternero != null && enfermedad != null && fechaDesde != null && fechaH != null) {
			Date fechaHasta = format.parse(fechaH);

			try {
				if (ValidacionDatos.validarEventoClinico2(fechaDesde, fechaHasta, observacion)) {

					Date fechaNac = ternero.getFechaNac();
					Date fechaDesde1 = fechaDesde;

					long diasVida1 = ((int) ((fechaDesde1.getTime() / (24 * 60 * 60 * 1000))
							- (int) (fechaNac.getTime() / (24 * 60 * 60 * 1000))));

					Long diasVida = diasVida1;

					EventoClinico ec = new EventoClinico(fechaDesde, enfermedad, ternero, fechaHasta, observacion,
							diasVida);
					eventoClinicoBean.guardarEventoClinico(ec);

				}
			} catch (Exception e) {
				e.printStackTrace();
				return "No se pudo completar el registro!";
			}
			return "Registro Completado";

		}
		return "Registro Completado";
	}
		
	@GET
	@Path("/enfermedades")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<EnfermedadDTO> getEnfermedades() throws MyException {
		List<EnfermedadDTO> colEnfermedades = new ArrayList<EnfermedadDTO>();

		colEnfermedades = enfermedadBean.obtenerTodosEnfermedad();
		HashMap<Long, EnfermedadDTO> alimentos = new HashMap<Long, EnfermedadDTO>();

		for (EnfermedadDTO a : colEnfermedades) {
			alimentos.put(a.getIdEnfermedad(), a);

		}
		return new ArrayList<EnfermedadDTO>(alimentos.values());

	}
		
	@GET
	@Path("/eventosClinicos")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<EventoClinicoRestDTO> getEventosClinicosTodo() throws MyException, ParseException {
		List<EventoClinicoDTO> colAlimentos = new ArrayList<EventoClinicoDTO>();

		colAlimentos = eventoClinicoBean.obtenerTodosEventosClinicosList();
		HashMap<Long, EventoClinicoRestDTO> alimentos = new HashMap<Long, EventoClinicoRestDTO>();

		for (EventoClinicoDTO ec : colAlimentos) {
			
			Date fechaNac = ec.getFechaNacimiento();
			Date fechaDesde = ec.getFechaDesde();
			Date fechaHasta = ec.getFechaHasta();
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
			String fechaNacimiento = format.format(fechaNac);
			String fechaDe = format.format(fechaDesde);
			
			if(fechaHasta == null) {
				
				String asd = "01/01/2000";

				fechaHasta = new SimpleDateFormat("dd/MM/yyyy").parse(asd);
				
			}
			String fechaHa = format.format(fechaHasta);


		
			EventoClinicoRestDTO unEvento = new EventoClinicoRestDTO(ec.getIdEventoClinico(), ec.getIdTernero(),fechaNacimiento,
					ec.getDiasVida(), fechaDe, fechaHa, ec.getNombreEnfermedad(), ec.getGravedad());

					alimentos.put(unEvento.getIdEventoClinico(), unEvento);

		}
		return new ArrayList<EventoClinicoRestDTO>(alimentos.values());

	}
	
}
