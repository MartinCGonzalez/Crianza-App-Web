package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.dtos.AlimentoDTO;
import com.entities.Alimento;

@Stateless
@LocalBean
public class AlimentosDAO {

	@PersistenceContext
	private EntityManager em;

	public void guardarAlimento(Alimento alimento) throws SQLException {
		this.em.persist(alimento);

	}

	public void actualizarAlimento(Alimento alimento) throws SQLException {
		this.em.merge(alimento);
	}

	public void borrarAlimento(Long idAlimento) throws SQLException {
		Alimento alimento = em.find(Alimento.class, idAlimento);
		em.remove(alimento);
		em.flush();
	}

	public Alimento obtenerAlimento(Long idAlimento) {
		return this.em.find(Alimento.class, idAlimento);
	}

	public List<Alimento> obternerTodos() throws SQLException {
		TypedQuery<Alimento> query = this.em.createQuery("SELECT a FROM Alimento a", Alimento.class);
		return query.getResultList();
	}
	
	public List<AlimentoDTO> obtenerAlimentos() throws SQLException {
		TypedQuery<AlimentoDTO> query = this.em.createQuery("SELECT NEW com.dtos.AlimentoDTO(a.nombre) FROM Alimento a ORDER BY a.nombre", AlimentoDTO.class);
		return query.getResultList();
	}

	public Long obtenerporId(Long idAlimento) {
		this.em.find(Alimento.class, idAlimento);
		return idAlimento;

	}
	
	public Alimento obtenerPorNombre(String filtro) {
		TypedQuery<Alimento>  query =
		em.createQuery("SELECT a FROM Alimento a WHERE a.nombre LIKE :nombre",Alimento.class).
		setParameter("nombre", filtro);
		return query.getSingleResult();
	}

	public List<AlimentoDTO> obtenerTodosAlimentosList()  throws SQLException{
		TypedQuery<AlimentoDTO> query = this.em.createQuery(
				"SELECT NEW com.dtos.AlimentoDTO(a.idAlimento,a.nombre,a.costoUnidad,a.cantidad, a.unidad.unidad) FROM Alimento a",
				AlimentoDTO.class);
		return query.getResultList();
	}

}
