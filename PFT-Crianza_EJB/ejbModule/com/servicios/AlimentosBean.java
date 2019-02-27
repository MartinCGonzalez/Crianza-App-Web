package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.AlimentosDAO;
import com.dtos.AlimentoDTO;
import com.entities.Alimento;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class Alimento
 */
@Stateless
@LocalBean
public class AlimentosBean implements AlimentosBeanRemote {

	/**
	 * Default constructor.
	 */

	@EJB
	AlimentosDAO alimentosDAO;

	public AlimentosBean() {
	}

	@Override
	public void alta(Alimento alimento) throws ServiciosException {

		try {
			this.alimentosDAO.guardarAlimento(alimento);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public Alimento altaAlimento(Alimento alimento) throws ServiciosException {
		try {
			this.alimentosDAO.guardarAlimento(alimento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alimento;
	}


	@Override
	public void actualizar(Alimento alimento) throws ServiciosException {

		try {
			this.alimentosDAO.actualizarAlimento(alimento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void borrar(Long idAlimento) throws ServiciosException {

		try {
			this.alimentosDAO.borrarAlimento(idAlimento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Long obtenerPorId(Long id) throws ServiciosException{
		return this.alimentosDAO.obtenerporId(id);
	}

	@Override
	public List<Alimento> obtenerTodos() throws SQLException{
		
		return this.alimentosDAO.obternerTodos();
	}
	
	@Override
	public List<AlimentoDTO> obtenerTodosAlimentos() throws MyException {
		try {
			return this.alimentosDAO.obtenerAlimentos();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new MyException("Ocurrió un error al obtener listado de Alimentos");

		}
	}

	@Override
	public Alimento obtenerAlimento(Long idAlimento) throws ServiciosException {
		return this.alimentosDAO.obtenerAlimento(idAlimento);
	}
	
	@Override
	public Alimento obtenerPorNombre(String filtro) {
		
		return this.alimentosDAO.obtenerPorNombre(filtro);
	}

	@Override
	public List<AlimentoDTO> obtenerTodosAlimentosList() throws MyException {
		try {
			return this.alimentosDAO.obtenerTodosAlimentosList();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new MyException("Ocurrió un error al obtener el listado de Clientes");
		}
	}


}
