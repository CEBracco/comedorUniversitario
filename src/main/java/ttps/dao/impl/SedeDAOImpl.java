package ttps.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.SedeDAO;
import unlp.comedor.Sede;

@Repository
@Transactional
public class SedeDAOImpl implements SedeDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void createSede(Sede sede) {
//		entityManager.persist(sede);
		entityManager.merge(sede);
	}

	@Override
	public Sede updateSede(Sede sede) {
		entityManager.merge(sede);
		return sede;
	}

	@Override
	public void deleteSede(long id) {
		entityManager.remove(entityManager.find(Sede.class, id));
	}

	@Override
	public List<Sede> getAllSedes() {
		Query query = entityManager.createQuery("SELECT e FROM Sede e");
		return (List<Sede>)query.getResultList();
	}

	@Override
	public Sede getSede(long id) {
		return (Sede) entityManager.find(Sede.class, id);
	}


}
