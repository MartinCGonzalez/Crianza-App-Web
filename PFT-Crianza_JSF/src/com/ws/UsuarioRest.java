package com.ws;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dtos.UsuarioDTO;
import com.dtos.UsuarioRestDTO;
import com.entities.Usuario;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.UsuarioBeanRemote;

@Stateless
@Path("/usuario")
@Produces("text/plain")
public class UsuarioRest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsuarioBeanRemote usuarioBean;

	// Ingresar Usuario
	@POST
	@Path("/ingresoUsuario")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String altaUsuario(UsuarioDTO usuario)
			throws MyException, ParseException, ServiciosException, SQLException {

		String nombre = usuario.getNombre();

		String apellido = usuario.getApellido();

		String contraseña = usuario.getContraseña();

		String contraseñaEncriptada = getHash(contraseña, "MD5");

		String perfil = usuario.getPerfil();

		String[] nombre1 = nombre.trim().split("\\s+");
		String[] apellido1 = apellido.trim().split("\\s+");

		String usu = nombre1[0].toLowerCase() + "." + apellido1[0].toLowerCase();
		String usuario2 = nombre1[0].toLowerCase() + "." + apellido1[0].toLowerCase() + "."
				+ apellido1[1].toLowerCase().charAt(0);

		try {

			Usuario u = new Usuario(apellido, contraseñaEncriptada, nombre, perfil, usu);

			usuarioBean.alta(u);

			if (usuarioBean.obtenerUsuarioIgual(usuario2) == null) {

				Usuario u2 = new Usuario(apellido, contraseñaEncriptada, nombre, perfil, usuario2);

				usuarioBean.alta(u2);
			}

		} catch (Exception e) {
			return "No se pudo ingresar el Usuario";
		}
		return "Usuario ingresado correctamente";
	}
	
	// Modificar Usuario
	@PUT
	@Path("/modificarUsuario")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String modificarUsuario(UsuarioDTO usuario)
			throws MyException, ParseException, ServiciosException, SQLException {

    	
		// Usuario
		long idUsuario1 = usuario.getIdUsuario();

		Usuario u = usuarioBean.obtenerUsuario(idUsuario1);

		String contraseña = usuario.getContraseña();
		
		String contraseñaEncriptada = getHash(contraseña , "MD5");


		try {

			u.setContraseña(contraseñaEncriptada);
			usuarioBean.actualizar(u);

			return "Usuario actualizado";

		} catch (Exception e) {
			return "No se pudo actualizar el Usuario";
		}
	}
	
	// Baja Usuario
	@DELETE
	@Path("/bajaUsuario")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String bajaUsuario(UsuarioDTO usuario)
			throws MyException, ParseException, ServiciosException, SQLException {
  	

		Long idUsuario = usuario.getIdUsuario();

		try {

			usuarioBean.borrar(idUsuario);
			
			return "Usuario eliminado correctamente!";

		} catch (Exception e) {
		return "No se pudo dar de baja al Usuario";
		
		}
	}
	
	@GET
	@Path("/loginUsuario/{usuario}/{contrasenia}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<UsuarioDTO> getUsuariosTodo(@PathParam("usuario") String usuario,@PathParam("contrasenia") String contrasenia) throws MyException {
		List<UsuarioDTO> colUsuarios = new ArrayList<UsuarioDTO>();
		
		colUsuarios = usuarioBean.obtenerUsuarioIgualList(usuario, contrasenia = getHash(contrasenia, "MD5"));
		
		HashMap<String, UsuarioDTO> usuarios = new HashMap<String, UsuarioDTO>();

		for (UsuarioDTO u : colUsuarios) {
			UsuarioRestDTO usuarioRest = new UsuarioRestDTO(u.getUsuario(), u.getContraseña(), u.getPerfil());
			usuarios.put(usuarioRest.getUsuario(), u);

		}
		return new ArrayList<UsuarioDTO>(usuarios.values());

	}
	
	@GET
	@Path("/usuariosTodo")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<UsuarioDTO> getUsuariosTodo() throws MyException {
		List<UsuarioDTO> colUsuarios = new ArrayList<UsuarioDTO>();

		colUsuarios = usuarioBean.obtenerTodosUsuariosList();
		HashMap<Long, UsuarioDTO> usuarios = new HashMap<Long, UsuarioDTO>();

		for (UsuarioDTO a : colUsuarios) {
			usuarios.put(a.getIdUsuario(), a);

		}
		return new ArrayList<UsuarioDTO>(usuarios.values());

	}
	
		
    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
