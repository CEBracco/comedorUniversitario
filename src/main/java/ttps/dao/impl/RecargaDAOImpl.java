package ttps.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.RecargaDAO;
import unlp.comedor.Recarga;
import unlp.comedor.Reserva;

@Repository
@Transactional
public class RecargaDAOImpl implements RecargaDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Recarga createRecarga(Recarga recarga) {
		return entityManager.merge(recarga);
	}

	@Override
	public Recarga updateRecarga(Recarga recarga) {
		entityManager.merge(recarga);
		return recarga;
	}

	@Override
	public void deleteRecarga(long id) {
		entityManager.remove(entityManager.find(Recarga.class, id));
	}

	@Override
	public List<Recarga> getAllRecargas() {
		Query query = entityManager.createQuery("SELECT e FROM Recarga e where activo=1");
		return (List<Recarga>)query.getResultList();
	}

	@Override
	public Recarga getRecarga(long id) {
		return (Recarga) entityManager.find(Recarga.class, id);
	}
	@Override
	 public List<Recarga> getAllRecargasSede(long idSede){
		Query query = entityManager.createQuery("SELECT e FROM Recarga e where sede_id=:id");
		query.setParameter("id", idSede);
		return (List<Recarga>)query.getResultList();
	 }

}
