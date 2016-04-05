package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.DiaDAO;
import unlp.comedor.Dia;
@Repository
@Transactional
public class DiaDAOImpl implements DiaDAO {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Dia createDia(Dia dia) {
		return entityManager.merge(dia);
	}

	@Override
	public Dia updateDia(Dia dia) {
		entityManager.merge(dia);
		return dia;
	}

	@Override
	public void deleteDia(long id) {
		entityManager.remove(entityManager.find(Dia.class, id));

	}

	@Override
	public List<Dia> getAllDias() {
		Query query = entityManager.createQuery("SELECT e FROM Dia e");
		return (List<Dia>)query.getResultList();
	}

	@Override
	public Dia getDia(long id) {
		return (Dia) entityManager.find(Dia.class, id);
	}

}
