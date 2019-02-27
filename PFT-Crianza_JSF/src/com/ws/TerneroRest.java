package com.ws;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dtos.TerneroDTO;
import com.dtos.TerneroRestDTO;
import com.entities.Ternero;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.TernerosBeanRemote;
import com.validaciones.ValidacionDatos;

@Stateless
@Path("/ternero")
@Produces("text/plain")
public class TerneroRest {
	
	@EJB
	private TernerosBeanRemote ternerosBean;
	
	// Ingresar Ternero
	@SuppressWarnings("null")
	@POST
	@Path("/ingresoTernero")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String altaTernero(TerneroRestDTO ternero) throws MyException, ParseException, ServiciosException, SQLException {

		long nroCaravana = ternero.getNroCaravana();
		
		// Comprueba nroCarava no este ingresado
		for (Long s : comprobarNroCaravana()) {
			if (nroCaravana == s) {

				//Se le asigna null si ya existe
				nroCaravana = (Long) null;
				
				}
		}
							
		BigDecimal pesoNac = ternero.getPesoNac();
		
		String fechaN = ternero.getFechaNac();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fechaNac = format.parse(fechaN);
		
		String parto = ternero.getParto();
		
		String raza = ternero.getRaza();
		
		long idGuachera = ternero.getIdGuachera();
		
		long idMadre = ternero.getIdMadre();
		
		long idPadre = ternero.getIdPadre();
		
		try {
			
			if(ValidacionDatos.validarTernero(nroCaravana, pesoNac, fechaNac)) {
			
			Ternero t = new Ternero(pesoNac, fechaNac, nroCaravana, parto, raza, idGuachera, idMadre, idPadre);	
			ternerosBean.alta(t);
			
			}
			
		} catch (Exception e) {
			
			return "No se pudo ingresar el Ternero";

		}
		return "Ternero ingresado correctamente";

	}
	

	//Comprueba con la BD el nroCaravana
	public LinkedList<Long> comprobarNroCaravana() throws MyException, ServiciosException, SQLException {
		
		// obtengo los nombres de la bd
		LinkedList<Long> terneros = new LinkedList<>();

		for (Ternero t : ternerosBean.obtenerTodos()) {
			terneros.add(t.getIdTernero());
		}

		return terneros;

	}
	

    //Obtiene todos los terneros
	@GET
	@Path("/ternerosRegistroConsumo")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<TerneroRestDTO> getTernerosTodo() throws MyException {
		List<TerneroDTO> colTerneros = new ArrayList<TerneroDTO>();

		colTerneros = ternerosBean.obtenerTernerosList();
		HashMap<Long, TerneroRestDTO> usuarios = new HashMap<Long, TerneroRestDTO>();

		for (TerneroDTO t : colTerneros) {
			TerneroRestDTO terneroRest = new TerneroRestDTO(t.getIdTernero(), t.getNroCaravana(), t.getIdGuachera());
			usuarios.put(terneroRest.getIdTernero(), terneroRest);

		}
		return new ArrayList<TerneroRestDTO>(usuarios.values());

	}
}
