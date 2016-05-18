package ttps.controller;

import java.util.GregorianCalendar;
import java.util.List;

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
import unlp.comedor.Menu;
import unlp.comedor.Reserva;
import unlp.comedor.Sede;
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
	
	
	@RequestMapping("buyTicket")//controlar si el usuario ya compro
    public ModelAndView buyTicket(@RequestParam long sede) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sessionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sessionRole.equals("Comensal")){
    		Cartilla cartillaActual=CartillaDAO.getCartillaActual();
    		
    		ModelAndView vista= new ModelAndView("buyTicket");
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sessionRole);
    		vista.addObject("cartilla", cartillaActual);
    		vista.addObject("sede", sede);
    		
    		for (Reserva reserva : ReservaDAO.getReservasActivas(sessionUser.getId(), sede)) {
				System.out.println(reserva.getId());
			}
    		
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
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
    		
    		for (long idDia : dias) {
    			if(idDia != 0){
    				Menu menuElegido= MenuDAO.getMenu(idDia);

    				Reserva reserva=new Reserva(ticket, startWeek.getTime(), menuElegido);
    				//reserva= ReservaDAO.createReserva(reserva);

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
	
}
