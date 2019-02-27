package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;

import com.dao.UsuarioDAO;
import com.dtos.AlimentoDTO;
import com.dtos.UsuarioDTO;
import com.entities.Usuario;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
@LocalBean
public class UsuarioBean implements UsuarioBeanRemote {

	@EJB
	UsuarioDAO usuarioDAO;
	
	public UsuarioBean() {
		
	}
	
	@Override
	public void alta(Usuario usuario) throws ServiciosException {

		try {
			this.usuarioDAO.guardarUsuario(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actualizar(Usuario usuario) throws ServiciosException {

		try {
			this.usuarioDAO.actualizarUsuario(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void borrar(Long idUsuario) throws ServiciosException {

		try {
			this.usuarioDAO.borrarUsuario(idUsuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Long obtenerPorId(Long id) throws ServiciosException{
		return this.usuarioDAO.obtenerporId(id);
	}

	@Override
	public List<Usuario> obtenerTodos(){
		return this.usuarioDAO.obternerTodos();
	}

	@Override
	public Usuario obtenerUsuario(Long idUsuario) throws ServiciosException {
		return this.usuarioDAO.obtenerUsuario(idUsuario);
	}
	
	@Override 
	public Usuario obtenerUsuarioIgual(String usuario) throws ServiciosException {
		return this.usuarioDAO.obtenerUsuarioIgual(usuario);
	}
	
	@Override
	public List<UsuarioDTO> obtenerTodosUsuariosList() throws MyException {
		try {
			return this.usuarioDAO.obtenerTodosAlimentosList();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new MyException("Ocurrió un error al obtener el listado de Usuarios");
		}
	}

	@Override
	public List<UsuarioDTO> obtenerUsuarioIgualList(String usuario, String contraseña) throws MyException {
		try {
			return this.usuarioDAO.obtenerUsuarioIgualList(usuario, contraseña);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new MyException("Ocurrió un error al obtener el usuario");
		}
	}
	
}