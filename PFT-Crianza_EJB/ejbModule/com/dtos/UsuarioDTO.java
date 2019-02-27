package com.dtos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.OneToMany;

import com.entities.Guachera;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long idUsuario;
	private String apellido;
	private String contrase�a;
	private String nombre;
	private String perfil;
	private String usuario;
	private List<Guachera> guacheras;
	
	public UsuarioDTO() {
		super();
	}
	
	public UsuarioDTO(String apellido, String contrase�a, String nombre, String perfil, String usuario) {
		super();
		this.apellido = apellido;
		this.contrase�a = contrase�a;
		this.nombre = nombre;
		this.perfil = perfil;
		this.usuario = usuario;
	}
	
	
	
		
	public UsuarioDTO(String usuario, String contrase�a, String perfil) {
		super();
		this.contrase�a = contrase�a;
		this.perfil = perfil;
		this.usuario = usuario;
	}

	public UsuarioDTO(long idUsuario, String nombre, String apellido, String perfil, String usuario,
			String contrase�a) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.perfil = perfil;
		this.usuario = usuario;
		this.contrase�a = contrase�a;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Guachera> getGuacheras() {
		return guacheras;
	}

	public void setGuacheras(List<Guachera> guacheras) {
		this.guacheras = guacheras;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", apellido=" + apellido + ", contrase�a=" + contrase�a + ", nombre="
				+ nombre + ", perfil=" + perfil + ", usuario=" + usuario + "]";
	}

}
