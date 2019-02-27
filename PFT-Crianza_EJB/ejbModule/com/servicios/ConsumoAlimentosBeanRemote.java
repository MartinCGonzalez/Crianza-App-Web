package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.dtos.ConsumoAlimentoDTO;
import com.entities.ConsumoAlimento;
import com.exceptions.MyException;
import com.exceptions.ServiciosException;

@Remote
public interface ConsumoAlimentosBeanRemote {

	public void actualizar(ConsumoAlimento consumoAlimento) throws ServiciosException;

	public void borrar(Long id) throws ServiciosException;

	public void alta(ConsumoAlimento consumoAlimento) throws ServiciosException;

	public List<ConsumoAlimento> obtenerTodos() throws ServiciosException;

	public List<ConsumoAlimento> obtenerPorTernero(Long idTernero) throws ServiciosException, SQLException;

	public List<ConsumoAlimentoDTO> obtenerTodosConsumosList() throws MyException;

}
