package com.ws;

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

import com.dtos.PesoDTO;
import com.dtos.PesoRestDTO;
import com.entities.Peso;
import com.entities.Ternero;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.PesoBeanRemote;
import com.servicios.TernerosBean;

@Stateless
@Path("/peso")
@Produces("text/plain")
public class PesoRest {

	@EJB
	private PesoBeanRemote pesoBean;

	@EJB
	private TernerosBean ternerosBean;

	// Ingresar Peso
	@POST
	@Path("/ingresoGananciaPeso")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String altaGananciaPeso(PesoRestDTO peso) throws MyException, ParseException, ServiciosException, SQLException {

		// TERNERO
		Long idTernero = peso.getIdTernero();

		Ternero ternero = new Ternero();

		try {
			ternero = ternerosBean.obtenerTernero(idTernero);

		} catch (ServiciosException ex) {
			ex.printStackTrace();
		}

		String tipoRegistro = peso.getTipoRegistro();
		
		String fec = peso.getFecha();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Date fecha = format.parse(fec);
		
		double pesoNacimiento = ternero.getPesoNac().doubleValue();

		BigDecimal peso2 = peso.getPeso();

		double peso3 = peso2.doubleValue();

		double ganancia = 0;
		
		try {
		
		// Si existe un registro en la tabla
		if (ternero.getListaRegPeso().size() >= 1) {

			int ultimo = ternero.getListaRegPeso().size() - 1;
			Peso ultimo1 = ternero.getListaRegPeso().get(ultimo);

			double urp = ultimo1.getPeso().doubleValue();
			double peso1 = peso3;

			ganancia = peso1 - urp;
		
			BigDecimal ganancia3 = BigDecimal.valueOf(ganancia);

			Peso p = new Peso(fecha, peso2, tipoRegistro, ternero, idTernero, ganancia3);
			pesoBean.alta(p);
		}
		
		// Sino existe un registro en la tabla
		if (ternero.getListaRegPeso().size() < 1) {

			double ganancia1 = peso3 - pesoNacimiento;

			BigDecimal ganancia4 = BigDecimal.valueOf(ganancia1);

			Peso p1 = new Peso(fecha, peso2, tipoRegistro, ternero, idTernero, ganancia4);
			pesoBean.alta(p1);

		}
		
		} catch (Exception e) {
			return "No se pudo completar el registro!";
		}

		return "Registro completado";
	}


	//Obtiene todos los registros de peso
	@GET
	@Path("/pesoTodo")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<PesoRestDTO> getpesosTodo() throws MyException {
		List<PesoDTO> colPesos = new ArrayList<PesoDTO>();

		colPesos = pesoBean.obtenerTodosPesosList();
		HashMap<Long, PesoRestDTO> pesos = new HashMap<Long, PesoRestDTO>();

		for (PesoDTO p : colPesos) {
			
			Date fechaPes = p.getFecha();
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			String fecha = format.format(fechaPes);

			PesoRestDTO unPeso = new PesoRestDTO(p.getIdPeso(), p.getIdTernero(), fecha, p.getPeso(), p.getGanancia(), p.getTipoRegistro());
			
			pesos.put(unPeso.getIdPeso(), unPeso);

		}
		return new ArrayList<PesoRestDTO>(pesos.values());

	}

}
