package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.PesoDAO;
import com.dtos.PesoDTO;
import com.entities.Peso;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;

/**
 * Session Bean implementation class Peso
 */
@Stateless
@LocalBean
public class PesoBean implements PesoBeanRemote {

    /**
     * Default constructor. 
     */
	
	@EJB
	PesoDAO pesoDAO;
	
    public PesoBean() {
    }
    
	@Override
	public void alta(Peso peso) throws ServiciosException {

		try {
			this.pesoDAO.guardarPeso(peso);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actualizar(Peso peso) throws ServiciosException {

		try {
			this.pesoDAO.actualizarPeso(peso);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void borrar(Long idPeso) throws ServiciosException {

		try {
			this.pesoDAO.borrarPeso(idPeso);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Peso> obtenerTodos(){
		
		return this.pesoDAO.obternerTodos();
	}

	@Override
	public Peso obtenerPeso(Long idPeso) throws ServiciosException {
		
		return this.pesoDAO.obtenerPeso(idPeso);
	}

	@Override
	public List<Peso> obtenerPorId(Long id) throws ServiciosException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Peso> obtenerPorTernero(Long idTernero) throws ServiciosException, SQLException {
		return this.pesoDAO.obtenerPorTernero(idTernero);
	}

	public List<PesoDTO> obtenerTodosPesosList() throws MyException {
		try {
			return this.pesoDAO.obtenerTodosAlimentosList();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new MyException("Ocurrió un error al obtener el listado de Peso");
		}
	}

	
    
}
