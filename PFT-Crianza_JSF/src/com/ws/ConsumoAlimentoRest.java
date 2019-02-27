package com.ws;

import java.io.Serializable;
import java.math.BigDecimal;
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

import com.dtos.ConsumoAlimentoDTO;
import com.dtos.ConsumoAlimentoRestDTO;
import com.entities.Alimento;
import com.entities.ConsumoAlimento;
import com.entities.Ternero;
import com.entities.Unidad;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.AlimentosBean;
import com.servicios.ConsumoAlimentosBeanRemote;
import com.servicios.TernerosBean;
import com.servicios.UnidadBean;


@Stateless
@Path("/consumoAlimento")
@Produces("text/plain")
public class ConsumoAlimentoRest implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private AlimentosBean alimentosBean;

	@EJB
	private ConsumoAlimentosBeanRemote consumoAlimentosBean;

	@EJB
	private TernerosBean ternerosBean;

	@EJB
	private UnidadBean unidadBean;

	// Ingreso Consumo Alimento
	@POST
	@Path("/ingresoConsumoAlimento")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String altaConsumoAlimento(ConsumoAlimentoRestDTO consAli)
			throws MyException, ParseException, ServiciosException {

		BigDecimal cantidad = consAli.getCantidad();
		
		String fec = consAli.getFecha();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fecha = format.parse(fec);

		long idAlimento = consAli.getIdAlimento();

		Alimento alimento = new Alimento();

		alimento = alimentosBean.obtenerAlimento(idAlimento);

		long idUnidad = alimento.getUnidad().getIdUnidad();

		Unidad unidad = unidadBean.obtenerUnidad(idUnidad);

		long idTernero = consAli.getIdTernero();

		Ternero ternero = new Ternero();

		try {
			ternero = ternerosBean.obtenerTernero(idTernero);

		} catch (ServiciosException ex) {
			ex.printStackTrace();
		}

		BigDecimal stockAlimento = alimento.getCantidad();

		double cantidadAlimento = cantidad.doubleValue();

		double d = stockAlimento.doubleValue();

		Double cantidadRestante = d - cantidadAlimento;

		BigDecimal c1 = BigDecimal.valueOf(cantidadRestante);

		// NUEVOS ATRIBUTOS DE ALIMENTO
		try {

			ConsumoAlimento c = new ConsumoAlimento(cantidad, fecha, alimento, unidad, ternero);
				consumoAlimentosBean.alta(c);
				alimento.setCantidad(c1);
				alimentosBean.actualizar(alimento);
				
				return "Registro completo";

			
		} catch (Exception e) {
			return "No se pudo completar el registro!";
		}
	}

	
	// Obtiene todos los registros de consumo de alimentos
	@GET
	@Path("/consumoAlimentosTodo")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<ConsumoAlimentoRestDTO> getConsumoAlimento() throws SQLException, ServiciosException, MyException {
		List<ConsumoAlimentoDTO> colConsumo = new ArrayList<ConsumoAlimentoDTO>();
		
		colConsumo = consumoAlimentosBean.obtenerTodosConsumosList();
		HashMap<Long, ConsumoAlimentoRestDTO> consumos = new HashMap<Long, ConsumoAlimentoRestDTO>();

		for (ConsumoAlimentoDTO c : colConsumo) {
			
			Date fechaCons = c.getFecha();
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
			String fecha = format.format(fechaCons);
			
			ConsumoAlimentoRestDTO unConsumo = new ConsumoAlimentoRestDTO(c.getIdConsumoAlimento(),c.getIdTernero(),c.getNroCaravana(),fecha
					,c.getAlimento(),c.getCantidad(),c.getUnidad());
			
			consumos.put(unConsumo.getIdConsumoAlimento(), unConsumo);

		}
		return new ArrayList<ConsumoAlimentoRestDTO>(consumos.values());
	}
	



}
