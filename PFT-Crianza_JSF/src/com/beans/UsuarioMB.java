package com.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import com.dtos.UsuarioDTO;
import com.entities.Usuario;
import com.enums.TipoUsuario;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;
import com.servicios.UsuarioBean;
import com.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public UsuarioMB() {
		if(usuario == null) {
			usuario = new UsuarioDTO();
		}
	}
	
	@EJB
	private UsuarioBean usuarioBean;
	
	private UsuarioDTO usuario = new UsuarioDTO();
	
	private Long idUsuario;
	
	private String nombre;
	
	private String apellido;
	
	private String contraseña;
	
	private String perfil;
	
	private String usuarioIngreso;
	
	private Usuario usuarioSeleccionado;
	
    public void onRowSelect( SelectEvent event_ )
    {
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
    
    public boolean esAdmin = true;

	public String altaUsuario() throws ServiciosException {
		
    	String mostrar = null;
    	
		String nombre = usuario.getNombre();

		String apellido = usuario.getApellido();

		String contraseña = usuario.getContraseña();
		
		String contraseñaEncriptada = getHash(contraseña , "MD5");
		
		String perfil = usuario.getPerfil();

		String[] nombre1 = nombre.trim().split("\\s+");
		String[] apellido1 = apellido.trim().split("\\s+");

		String usuario = nombre1[0].toLowerCase() + "." + apellido1[0].toLowerCase();
		String usuario2 = nombre1[0].toLowerCase() + "." + apellido1[0].toLowerCase() + "."
				+ apellido1[1].toLowerCase().charAt(0);

		Usuario usuario1 = usuarioBean.obtenerUsuarioIgual(usuario);

		try {

			if (usuario1 == null) {

				Usuario u = new Usuario(apellido, contraseñaEncriptada, nombre, perfil, usuario);

				usuarioBean.alta(u);
			
				FacesMessage msg = new FacesMessage( "Usuario registrado " + usuario);
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} else if (usuarioBean.obtenerUsuarioIgual(usuario2) == null) {

				Usuario u = new Usuario(apellido, contraseñaEncriptada, nombre, perfil, usuario2);

				usuarioBean.alta(u);
				
				FacesMessage msg = new FacesMessage( "Usuario registrado " + usuario2);
				FacesContext.getCurrentInstance().addMessage(null, msg);

			} else {

				FacesMessage msg = new FacesMessage( "Usuario ya existente, por favor revise sus datos");
				FacesContext.getCurrentInstance().addMessage(null, msg);

			}

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage( "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
		return mostrar;
	}
	
	public String modificarUsuario() throws ServiciosException {
		
    	String mostrar = null;
    	
		// Usuario
		long idUsuario1 = usuarioSeleccionado.getIdUsuario();

		Usuario u = usuarioBean.obtenerUsuario(idUsuario1);

		String contraseña = usuario.getContraseña();
		
		String contraseñaEncriptada = getHash(contraseña , "MD5");


		try {

			u.setContraseña(contraseñaEncriptada);
			usuarioBean.actualizar(u);

			FacesMessage msg = new FacesMessage( "Usuario actualizado");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage( "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
		return mostrar;

	}
	
	
	public String bajaUsuario() {
		
    	String mostrar = null;
    	

		Long idUsuario = usuario.getIdUsuario();

		try {

			usuarioBean.borrar(idUsuario);
			
			FacesMessage msg = new FacesMessage("Usuario eliminado correctamente!");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage( "ES NECESARIO INGRESAR TODOS LOS DATOS REQUERIDOS");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
		return mostrar;

	}
	
	
	public String validarUsuarioContraseña() throws ServiciosException {
		
		String nombre = usuario.getUsuario();
			
		String contraseña = usuario.getContraseña();
		
		String contraseñaEncriptada = getHash(contraseña , "MD5");
		
		Usuario usuario1 = null;
		
		usuario1 = usuarioBean.obtenerUsuarioIgual(nombre);

		String u1 = usuario1.getUsuario();
		
		String u2 = usuario1.getContraseña();
		
		String u3 = usuario1.getPerfil();
		
		if(u1.equals(nombre) && u2.equals(contraseñaEncriptada) && u3.equals("ADMINISTRADOR")) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", nombre);
			return "headerAdmin";
		} else 
			if(!u3.equals("ADMINISTRADOR")){
			return "header";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuario y/o Contraseña incorrectos",
							"Por favor ingrese un nombre y usuario correctos"));
			return "login";
		}
	}
	

	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
	
	public List<Usuario> getAllUsuarios() throws MyException {

		List<Usuario> usuarios = new LinkedList<Usuario>();
		usuarios = usuarioBean.obtenerTodos();

		return usuarios;

	}

	// Cargo los campos del combo tipo usuario
	public TipoUsuario[] getTipoUsuario() {
		return TipoUsuario.values();
	}

	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getUsuarioIngreso() {
		return usuarioIngreso;
	}

	public void setUsuarioIngreso(String usuarioIngreso) {
		this.usuarioIngreso = usuarioIngreso;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
		
}
