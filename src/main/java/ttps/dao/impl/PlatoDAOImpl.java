package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.PlatoDAO;
import unlp.comedor.Dia;
import unlp.comedor.Plato;
@Repository
@Transactional
public class PlatoDAOImpl implements PlatoDAO {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Plato createPlato(Plato plato) {
		return entityManager.merge(plato);

	}

	@Override
	public Plato updatePlato(Plato plato) {
		entityManager.merge(plato);
		return plato;
	}

//	@Override
//	public void deletePlato(long id) {
//		entityManager.remove(entityManager.find(Plato.class, id));
//
//	}
	
	@Override
	public void deletePlato(long id) {
		Query query = entityManager.createQuery("UPDATE Plato set activo=0 where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();

	}

	@Override
	public List<Plato> getAllPlatos() {
		Query query = entityManager.createQuery("SELECT e FROM Plato e where e.activo=1");
		return (List<Plato>)query.getResultList();
	}

	@Override
	public Plato getPlato(long id) {
		return (Plato) entityManager.find(Plato.class, id);
	}

}
