package com.dtos;

import java.io.Serializable;

public class UsuarioRestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String contraseña;
	private String perfil;
	
	public UsuarioRestDTO() {
		super();
	}

	public UsuarioRestDTO(String usuario, String contraseña, String perfil) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.perfil = perfil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	@Override
	public String toString() {
		return "UsuarioRestDTO [usuario=" + usuario + ", contraseña=" + contraseña + ", perfil=" + perfil + "]";
	}
	
	

}
