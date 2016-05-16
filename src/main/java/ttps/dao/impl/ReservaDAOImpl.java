package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.ReservaDAO;
import unlp.comedor.Reserva;
@Repository
@Transactional
public class ReservaDAOImpl implements ReservaDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Reserva createReserva(Reserva reserva) {
		return entityManager.merge(reserva);

	}

	@Override
	public Reserva updateReserva(Reserva reserva) {
		entityManager.merge(reserva);
		return reserva;
	}

	@Override
	public void deleteReserva(long id) {
		entityManager.remove(entityManager.find(Reserva.class, id));

	}

	@Override
	public List<Reserva> getAllReservas() {
		Query query = entityManager.createQuery("SELECT e FROM Reserva e");
		return (List<Reserva>)query.getResultList();
	}

	@Override
	public Reserva getReserva(long id) {
		return (Reserva) entityManager.find(Reserva.class, id);
	}

}
