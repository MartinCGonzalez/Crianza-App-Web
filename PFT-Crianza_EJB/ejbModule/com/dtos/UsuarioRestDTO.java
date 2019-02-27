package com.dtos;

import java.io.Serializable;

public class UsuarioRestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String contrase�a;
	private String perfil;
	
	public UsuarioRestDTO() {
		super();
	}

	public UsuarioRestDTO(String usuario, String contrase�a, String perfil) {
		super();
		this.usuario = usuario;
		this.contrase�a = contrase�a;
		this.perfil = perfil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "UsuarioRestDTO [usuario=" + usuario + ", contrase�a=" + contrase�a + ", perfil=" + perfil + "]";
	}
	
	

}
