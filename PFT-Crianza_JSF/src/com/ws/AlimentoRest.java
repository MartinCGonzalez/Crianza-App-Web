 package com.ws;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dtos.AlimentoDTO;
import com.entities.Alimento;
import com.entities.Unidad;
import com.enums.TipoAlimento;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.AlimentosBeanRemote;
import com.servicios.UnidadBeanRemote;

@Stateless
@Path("/alimento")
@Produces("text/plain")
public class AlimentoRest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private AlimentosBeanRemote alimentosBean;
	
	@EJB
	private UnidadBeanRemote unidadBean;
	
	// Ingresar Alimento
	@POST
	@Path("/ingresoAlimento")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String altaAlimento(AlimentoDTO alimento) throws MyException, ParseException, ServiciosException, SQLException {

		String nombre = alimento.getNombre();

		// Comprueba alimento no este en stock
		for (String s : comprobarNombre()) {
			if (nombre.equals(s)) {

				nombre = "Nombre Duplicado";
			}
		}

		TipoAlimento tipo = TipoAlimento.valueOf(nombre);
		
		long idAlimento = (long) tipo.getNumero();
		long idUnidad = (long) tipo.getIdUnidad();
		
		Unidad unidad = new Unidad();
		unidad.setIdUnidad(idUnidad);
		
		BigDecimal costoUnidad = alimento.getCostoUnidad();
		BigDecimal cantidadAli = alimento.getCantidad();
		// ALTA
		try {
			Alimento a = new Alimento(idAlimento, cantidadAli, costoUnidad, nombre, unidad);
			alimentosBean.alta(a);
		} catch (Exception e) {
			
			return "No se pudo ingresar el Alimento";
		}
		return "Alimento ingresado correctamente";
	}
	   
	// Modificar Alimento
	@PUT
	@Path("/modificarAlimento")
	@Produces(MediaType.APPLICATION_JSON + ";charset = utf-8")
	public String modificarAlimento(AlimentoDTO alimento) throws ServiciosException {

		long idAlimento = alimento.getIdAlimento();

		BigDecimal costoUnit = alimento.getCostoUnidad();

		BigDecimal cantidadAlime = alimento.getCantidad();

		Alimento a = alimentosBean.obtenerAlimento(idAlimento);

		// NUEVOS ATRIBUTOS DE ALIMENTO
		a.setCostoUnidad(costoUnit);
		a.setCantidad(cantidadAlime);

		alimentosBean.actualizar(a);

		return "Alimento modificado conrrectamente";

	}
	
	// Agregar Alimento
	@PUT
	@Path("/agregarAlimento")
	@Produces(MediaType.APPLICATION_JSON + ";charset = utf-8")
	public String agregarAlimento(AlimentoDTO alimento) throws ServiciosException {

		String nombre = (String) alimento.getNombre();

		BigDecimal cantidad = alimento.getCantidad();

		Alimento a = alimentosBean.obtenerPorNombre(nombre);

		a.setCantidad(a.getCantidad().add(cantidad));

		alimentosBean.actualizar(a);

		return "Alimento agregado conrrectamente";

	}
	
	//Comprueba con la BD el nombre del alimento
	public LinkedList<String> comprobarNombre() throws MyException, ServiciosException, SQLException {
		
		// obtengo los nombres de la bd
		LinkedList<String> nombres = new LinkedList<>();

		for (Alimento a : alimentosBean.obtenerTodos()) {
			nombres.add(a.getNombre());
		}

		return nombres;

	}
    
	@GET
	@Path("/alimentos")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<AlimentoDTO> getAlimentos() throws MyException {
		List<AlimentoDTO> colAlimentos = new ArrayList<AlimentoDTO>();

		colAlimentos = alimentosBean.obtenerTodosAlimentos();
		HashMap<String, AlimentoDTO> alimentos = new HashMap<String, AlimentoDTO>();

		for (AlimentoDTO a : colAlimentos) {
			alimentos.put(a.getNombre(), a);

		}
		return new ArrayList<AlimentoDTO>(alimentos.values());

	}
	
	@GET
	@Path("/alimentosTodo")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<AlimentoDTO> getAlimentosTodo() throws MyException {
		List<AlimentoDTO> colAlimentos = new ArrayList<AlimentoDTO>();

		colAlimentos = alimentosBean.obtenerTodosAlimentosList();
		HashMap<Long, AlimentoDTO> alimentos = new HashMap<Long, AlimentoDTO>();

		for (AlimentoDTO a : colAlimentos) {
			alimentos.put(a.getIdAlimento(), a);

		}
		return new ArrayList<AlimentoDTO>(alimentos.values());

	}
}

	
	



