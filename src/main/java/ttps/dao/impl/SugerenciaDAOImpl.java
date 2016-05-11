package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.SugerenciaDAO;
import unlp.comedor.Administrador;
import unlp.comedor.Sugerencia;
@Repository
@Transactional
public class SugerenciaDAOImpl implements SugerenciaDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Sugerencia createSugerencia(Sugerencia sugerencia) {
		return entityManager.merge(sugerencia);

	}

	@Override
	public Sugerencia updateSugerencia(Sugerencia sugerencia) {
		entityManager.merge(sugerencia);
		return sugerencia;
	}

	@Override
	public void deleteSugerencia(long id) {
		entityManager.remove(entityManager.find(Sugerencia.class, id));

	}

	@Override
	public List<Sugerencia> getAllSugerencias() {
		Query query = entityManager.createQuery("SELECT e FROM Sugerencia e where activo=1");
		return (List<Sugerencia>)query.getResultList();
	}

	@Override
	public Sugerencia getSugerencia(long id) {
		return (Sugerencia) entityManager.find(Sugerencia.class, id);
	}

}
