package com.dao;

import java.sql.SQLException;import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.dtos.PesoDTO;
import com.entities.Peso;


@Stateless
@LocalBean
public class PesoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void guardarPeso(Peso peso) throws SQLException {
		this.em.persist(peso);
	}
	
	public void actualizarPeso(Peso peso) throws SQLException {
		this.em.merge(peso);
	}

	public void borrarPeso(Long idPeso) throws SQLException {
		Peso peso = em.find(Peso.class, idPeso);
		em.remove(peso);
		em.flush();
	}

	public Peso obtenerPeso(Long idPeso) {

		return this.em.find(Peso.class, idPeso);
	}
	
	public List<Peso> obternerTodos() {
		
		TypedQuery<Peso> query = this.em.createQuery("SELECT p FROM Peso p", Peso.class);
		return query.getResultList();
	}
	
	public List<Peso> obtenerPorTernero(Long idTernero) throws SQLException {

		TypedQuery<Peso> query = em.createQuery("SELECT p FROM Peso p WHERE p.ID_TERNERO LIKE :id", Peso.class)
				.setParameter("id", idTernero);
		return query.getResultList();
	}

	public List<PesoDTO> obtenerTodosAlimentosList() throws SQLException{
		TypedQuery<PesoDTO> query = this.em.createQuery(
				"SELECT NEW com.dtos.PesoDTO(p.idPeso,p.idTernero,p.fecha,p.peso,p.ganancia, p.tipoRegistro) FROM Peso p ORDER BY p.fecha ASC",
				PesoDTO.class);
		return query.getResultList();
	}
	
}