package ttps.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ttps.dao.CartillaDAO;
import ttps.dao.MenuDAO;
import ttps.dao.ReservaDAO;
import ttps.dao.SedeDAO;
import ttps.dao.TicketDAO;
import ttps.dao.UserDAO;
import unlp.comedor.Cartilla;
import unlp.comedor.Comensal;
import unlp.comedor.Dia;
import unlp.comedor.Menu;
import unlp.comedor.Reserva;
import unlp.comedor.Ticket;
import unlp.comedor.Usuario;

@Controller
public class TicketController {
	
	@Autowired
	private HttpSession httpSession;

	@Autowired
    private TicketDAO TicketDAO;
	
	@Autowired
    private CartillaDAO CartillaDAO;
	
	@Autowired
    private MenuDAO MenuDAO;
	
	@Autowired
    private ReservaDAO ReservaDAO;
	
	@Autowired
    private UserDAO UserDAO;

	@Autowired
    private SedeDAO SedeDAO;
	
	
	@RequestMapping("selectSedeTicket")//controlar si el usuario ya compro
    public ModelAndView buyTicket() {
		Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sessionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sessionRole.equals("Comensal")){
    		
    		ModelAndView vista= new ModelAndView("selectSedeTicket");
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sessionRole);
    		vista.addObject("sedeList", SedeDAO.getAllSedes());
    		
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
	}
	
	
	@RequestMapping("buyTicket")//controlar si el usuario ya compro
    public ModelAndView buyTicket(@RequestParam(required=false) Long sede) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sessionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sessionRole.equals("Comensal") && sede != null){
    		Cartilla cartillaActual=CartillaDAO.getCartillaActual();
    		
    		ModelAndView vista= new ModelAndView("buyTicket");
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sessionRole);
    		vista.addObject("cartilla", cartillaActual);
    		vista.addObject("sede", sede);
    		
    		List<Reserva> reservasVigentes=this.getFullDaysReservations(sessionUser, sede);
    		if(!reservasVigentes.isEmpty()){
    			vista.addObject("reservasPorSemana", this.cutWeek(reservasVigentes));
    		}
    		
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:selectSedeTicket");
	    }
	}
	
	@RequestMapping("saveTicket")
    public ModelAndView saveTicket(@RequestParam long sede, @RequestParam long[] dias) {//controlar si ya compre para esa sede 
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sessionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null  && sessionRole.equals("Comensal")){
    		
    		Cartilla cartilla= CartillaDAO.getCartillaActual();

    		Ticket ticket= new Ticket(SedeDAO.getSede(sede), (Comensal)sessionUser, cartilla);
    		((Comensal)sessionUser).getTickets().add(ticket);
    		
    		GregorianCalendar startWeek= new GregorianCalendar();
    		this.setNextMonday(startWeek);
    		
    		for (long idDia : dias) {//fijarse si puedo sacar el index del dia con el index del foreach
    			if(idDia != 0){
    				Menu menuElegido= MenuDAO.getMenu(idDia);

    				Reserva reserva=new Reserva(ticket, startWeek.getTime(), menuElegido);

    				ticket.addReserva(reserva);
    			}
    			startWeek.add(GregorianCalendar.DATE, 1);
    			this.controlGregorianCalendar(startWeek);
			}
    		
    		TicketDAO.createTicket(ticket);
    		//UserDAO.updateUser(sessionUser);
    		
    		return new ModelAndView("redirect:buyTicket");
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
	}
	
	private List<Reserva> getFullDaysReservations(Usuario user, long sede){
		List<Reserva> activeReservations= ReservaDAO.getReservasActivas(user.getId(), sede);
		GregorianCalendar nextMonday=new GregorianCalendar();
		this.setNextMonday(nextMonday);
		
		List<Reserva> fullDaysReservation=new LinkedList<Reserva>();
		
		if(!activeReservations.isEmpty()){
			for (Reserva reserva : activeReservations) {

				while(!this.compareDates(reserva.getFecha(), nextMonday.getTime())){
					Reserva mockReservation=new Reserva();
					mockReservation.setFecha(nextMonday.getTime());
					mockReservation.setId((long)0);
					
					fullDaysReservation.add(mockReservation);
					nextMonday.add(GregorianCalendar.DATE, 1);
					this.controlGregorianCalendar(nextMonday);
				}
				fullDaysReservation.add(reserva);
				nextMonday.add(GregorianCalendar.DATE, 1);
				this.controlGregorianCalendar(nextMonday);
			}
			
			while(this.getDay(fullDaysReservation.get(fullDaysReservation.size() - 1).getFecha()) != GregorianCalendar.FRIDAY){
				Reserva mockReservation=new Reserva();
				mockReservation.setFecha(nextMonday.getTime());
				mockReservation.setId((long)0);
				
				fullDaysReservation.add(mockReservation);
				nextMonday.add(GregorianCalendar.DATE, 1);
			}
		}
		
		List<Dia> semana=CartillaDAO.getCartillaActual().getSemana();
		ListIterator<Dia> semanaIterator=semana.listIterator();
		for (Reserva reserva : fullDaysReservation) {
			if(!semanaIterator.hasNext()){
				semanaIterator=semana.listIterator();
			}
			reserva.setDia(semanaIterator.next());
		}
		
		return fullDaysReservation;
	}
	
	private List<List<Reserva>> cutWeek(List<Reserva> reservas){
		List<List<Reserva>> weeks = new ArrayList<List<Reserva>>();
		
		int numOfWeeks=reservas.size() / 5;
		
		for (int i = 0; i < numOfWeeks; i++) {
			weeks.add(reservas.subList(i*5, (i+1)*5));
		}
		
		return weeks;
	}
	
	/**
	 * Posiciona el calendario recibido en el proximo lunes 
	 */
	private void setNextMonday(GregorianCalendar startWeek) {
		
		startWeek.add(GregorianCalendar.DATE, 1);
		while (startWeek.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.MONDAY) {
		    startWeek.add(GregorianCalendar.DATE, 1);
		}
	}
	
	/**
	 * Ignora los fines de semana 
	 */
	private void controlGregorianCalendar(GregorianCalendar calendar) {
		if(calendar.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SATURDAY || calendar.get(GregorianCalendar.DAY_OF_WEEK) == GregorianCalendar.SUNDAY){
			this.setNextMonday(calendar);
		}
	}
	
	private int getDay(Date date){
		GregorianCalendar cal= new GregorianCalendar();
		cal.setTime(date);
		
		return cal.get(GregorianCalendar.DAY_OF_WEEK);
	}

	/**
	 * Compara fechas sin tener en cuenta el time
	 */
	private boolean compareDates(Date date1, Date date2){
		return this.getZeroTimeDate(date1).compareTo(this.getZeroTimeDate(date2)) == 0;
	}
	
	/**
	 * Retorna el objeto Date recibido con el time en 0 asi son comparados  
	 */
	public Date getZeroTimeDate(Date fecha) {
	    Date res = fecha;
	    GregorianCalendar calendar = new GregorianCalendar();

	    calendar.setTime( fecha );
	    calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
	    calendar.set(GregorianCalendar.MINUTE, 0);
	    calendar.set(GregorianCalendar.SECOND, 0);
	    calendar.set(GregorianCalendar.MILLISECOND, 0);

	    res = calendar.getTime();

	    return res;
	}

	
}
