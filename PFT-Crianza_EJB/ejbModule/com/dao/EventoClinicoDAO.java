package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.dtos.ConsumoAlimentoDTO;
import com.dtos.EventoClinicoDTO;
import com.dtos.EventoClinicoRestDTO;
import com.entities.EventoClinico;

/**
 * Session Bean implementation class EventoClinicoDAO
 */
@Stateless
@LocalBean
public class EventoClinicoDAO {

    @PersistenceContext
    private EntityManager em;
    
    public void guardarEventoClinico(EventoClinico e) throws SQLException{
    	this.em.persist(e);
    }
    
	public List<EventoClinico> obtenerTodos() {
		TypedQuery<EventoClinico> query = this.em.createQuery("SELECT ec FROM EventoClinico ec", EventoClinico.class);
		return query.getResultList();
	}
	
	public List<EventoClinicoDTO> obtenerTodosEventosClinicosList()  throws SQLException{
		TypedQuery<EventoClinicoDTO> query = this.em.createQuery(
				"SELECT NEW com.dtos.EventoClinicoDTO(ec.idEventoClinico, ec.ternero.idTernero, ec.ternero.fechaNac, ec.diasVida, ec.fechaDesde, ec.fechaHasta, ec.enfermedad.nombre, ec.enfermedad.gradoGravedad) FROM EventoClinico ec",
				EventoClinicoDTO.class);
		return query.getResultList();
	}
}
