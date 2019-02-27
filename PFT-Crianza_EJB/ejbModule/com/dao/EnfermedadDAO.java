package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.dtos.AlimentoDTO;
import com.dtos.EnfermedadDTO;
import com.entities.Enfermedad;

/**
 * Session Bean implementation class EnfermedadDAO
 */
@Stateless
@LocalBean
public class EnfermedadDAO {

	@PersistenceContext
    private EntityManager em;
	
    public void guardadEnfermedad(Enfermedad e){
    	this.em.persist(e);
    	this.em.flush();
    }
    
    public List<Enfermedad> obtenerTodas(){
    	TypedQuery<Enfermedad> query = this.em.createQuery("Select e From Enfermedad e", Enfermedad.class);
    	return query.getResultList();
    }
    
	public Enfermedad obtenerEnfermedad(Long idEnfermedad) {
		return this.em.find(Enfermedad.class, idEnfermedad);
	}
	
	public List<EnfermedadDTO> obtenerEnfermedades() throws SQLException {
		TypedQuery<EnfermedadDTO> query = this.em.createQuery("SELECT NEW com.dtos.EnfermedadDTO(e.idEnfermedad, e.nombre, e.gradoGravedad) FROM Enfermedad e", EnfermedadDTO.class);
		return query.getResultList();
	}

}
