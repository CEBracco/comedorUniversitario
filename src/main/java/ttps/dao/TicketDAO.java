package ttps.dao;

import java.util.List;

import unlp.comedor.Ticket;

public interface TicketDAO {
	public Ticket createTicket(Ticket ticket);
    public Ticket updateTicket(Ticket ticket);
    public void deleteTicket(long id);
    public List<Ticket> getAllTickets();
    public Ticket getTicket(long id);
    public List<Ticket> getAllTicketsSede(long idSede);
}
