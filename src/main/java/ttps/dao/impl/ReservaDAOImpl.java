package ttps.dao.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

	@Override
	public List<Reserva> getReservasActivas(long usuario, long sede) {
		Query query = entityManager.createQuery("from Reserva r "
												+ "where r.ticket.comensal.id=" + usuario + " and r.ticket.sede.id="+ sede
												+ "and r.fecha >= ?1");
		
		GregorianCalendar nextMonday= new GregorianCalendar();
		nextMonday.set(GregorianCalendar.HOUR_OF_DAY, 0);
		
		nextMonday.add(GregorianCalendar.DATE, 1);
		while (nextMonday.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.MONDAY){
			nextMonday.add(GregorianCalendar.DATE, 1);
		}
		
		query.setParameter(1, nextMonday.getTime());
		
		return (List<Reserva>)query.getResultList();
	}

}
