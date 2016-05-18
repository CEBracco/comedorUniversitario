package ttps.dao;

import java.util.List;

import unlp.comedor.Reserva;

public interface ReservaDAO {
	public Reserva createReserva(Reserva reserva);
    public Reserva updateReserva(Reserva reserva);
    public void deleteReserva(long id);
    public List<Reserva> getAllReservas();
    public Reserva getReserva(long id);
    public List<Reserva> getReservasActivas(long usuario, long sede);
}
