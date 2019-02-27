package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.dtos.ConsumoAlimentoDTO;
import com.dtos.EventoClinicoDTO;
import com.dtos.EventoClinicoRestDTO;
import com.entities.EventoClinico;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;

@Remote
public interface EventoClinicoBeanRemote {
	
	public void guardarEventoClinico(EventoClinico e) throws ServiciosException;
	
	List<EventoClinico> obtenerTodos() throws ServiciosException;
	
	public List<EventoClinicoDTO> obtenerTodosEventosClinicosList() throws MyException;



	
}
