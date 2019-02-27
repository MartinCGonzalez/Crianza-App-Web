package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.dtos.UsuarioDTO;
import com.entities.Usuario;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;

@Remote
public interface UsuarioBeanRemote {

	void alta(Usuario usuario) throws ServiciosException;

	void actualizar(Usuario usuario) throws ServiciosException;

	void borrar(Long idUsuario) throws ServiciosException;

	Long obtenerPorId(Long id) throws ServiciosException;

	List<Usuario> obtenerTodos();

	Usuario obtenerUsuario(Long idUsuario) throws ServiciosException;

	Usuario obtenerUsuarioIgual(String usuario) throws ServiciosException;
	
	public List<UsuarioDTO> obtenerTodosUsuariosList() throws MyException;
	
	public List<UsuarioDTO> obtenerUsuarioIgualList(String usuario, String contraseña) throws MyException;


}
