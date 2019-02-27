package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.EnfermedadDAO;
import com.dtos.EnfermedadDTO;
import com.entities.Enfermedad;
import com.exceptions.MyException;

/**
 * Session Bean implementation class EnfermedadBean
 */
@Stateless
@LocalBean
public class EnfermedadBean implements EnfermedadBeanRemote {

	@EJB
	private EnfermedadDAO enfermedadDAO;
	
	@Override
	public void guardadEnfermedad(Enfermedad e) {
		this.enfermedadDAO.guardadEnfermedad(e);
		
	}

	@Override
	public List<Enfermedad> obtenerTodos() {
		return this.enfermedadDAO.obtenerTodas();
	}

	@Override
	public Enfermedad obtenerEnfermedad(Long idEnfermedad) {
		return this.enfermedadDAO.obtenerEnfermedad(idEnfermedad);
	}

	@Override
	public List<EnfermedadDTO> obtenerTodosEnfermedad() throws MyException {
		try {
			return this.enfermedadDAO.obtenerEnfermedades();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new MyException("Ocurrió un error al obtener listado de Enfermedades");

		}
	}
	

}
