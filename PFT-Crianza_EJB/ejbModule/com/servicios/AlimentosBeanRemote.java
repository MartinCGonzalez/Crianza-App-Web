package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.dtos.AlimentoDTO;
import com.entities.Alimento;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;

@Remote
public interface AlimentosBeanRemote {

	public void alta(Alimento alimento) throws ServiciosException;

	public void actualizar(Alimento alimento) throws ServiciosException;

	public void borrar(Long idAlimento) throws ServiciosException;

	public List<Alimento> obtenerTodos() throws ServiciosException, SQLException;

	public Alimento obtenerAlimento(Long id) throws ServiciosException;

	public Long obtenerPorId(Long id) throws ServiciosException;

	public Alimento obtenerPorNombre(String filtro);

	public Alimento altaAlimento(Alimento alimento) throws ServiciosException;

	public List<AlimentoDTO> obtenerTodosAlimentos() throws MyException;

	public List<AlimentoDTO> obtenerTodosAlimentosList() throws MyException;
}
