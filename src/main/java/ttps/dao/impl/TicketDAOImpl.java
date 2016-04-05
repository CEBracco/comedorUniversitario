package ttps.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.dao.TicketDAO;
import unlp.comedor.Administrador;
import unlp.comedor.Ticket;
@Repository
@Transactional
public class TicketDAOImpl implements TicketDAO {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Ticket createTicket(Ticket ticket) {
		return entityManager.merge(ticket);

	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		entityManager.merge(ticket);
		return ticket;
	}

	@Override
	public void deleteTicket(long id) {
		entityManager.remove(entityManager.find(Ticket.class, id));

	}

	@Override
	public List<Ticket> getAllTickets() {
		Query query = entityManager.createQuery("SELECT e FROM ticket e");
		return (List<Ticket>)query.getResultList();
	}

	@Override
	public Ticket getTicket(long id) {
		return (Ticket) entityManager.find(Ticket.class, id);
	}

}
