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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView selectSedeTicket() {
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
    public ModelAndView buyTicket(@RequestParam(required=false) Long sede, final RedirectAttributes redirectAttributes) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sessionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sessionRole.equals("Comensal") && sede != null){
    		
    		if(!((Comensal)sessionUser).getRegular()){
    			redirectAttributes.addFlashAttribute("msj", "¡No estas habilitado para comprar tickets!");
    			return new ModelAndView("redirect:selectSedeTicket");
    		}
    		
    		Cartilla cartillaActual=CartillaDAO.getCartillaActual();
    		
    		ModelAndView vista= new ModelAndView("buyTicket");
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sessionRole);
    		vista.addObject("cartilla", cartillaActual);
    		vista.addObject("semanas", this.cutDaysOfWeek(cartillaActual.getSemana()));
    		vista.addObject("sede", sede);
    		
    		List<Reserva> reservasVigentes=this.getFullDaysReservations(sessionUser, sede);
    		if(!reservasVigentes.isEmpty()){
    			vista.addObject("reservasPorSemana", this.cutWeek(reservasVigentes));
    			System.out.println(this.cutWeek(reservasVigentes).size());
    		}
    		
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:selectSedeTicket");
	    }
	}
	
	@RequestMapping("saveTicket")
    public ModelAndView saveTicket(@RequestParam long sede, @RequestParam long[] dias) { 
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sessionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null  && sessionRole.equals("Comensal")){
    		
    		Comensal sessionComensal=(Comensal)sessionUser;
    		Cartilla cartilla= CartillaDAO.getCartillaActual();
    		
    		if(cartilla == null){
    			return new ModelAndView("redirect:index");
    		}
    		
    		if(sessionComensal.getSaldo() >= (this.calculatePrice(dias,cartilla.getPrecio()))){ //controlo si me alcanza el saldo
	
	    		Ticket ticket= new Ticket(SedeDAO.getSede(sede), (Comensal)sessionUser, cartilla);
	    		sessionComensal.getTickets().add(ticket);
	    		
	    		GregorianCalendar startWeek= new GregorianCalendar();
	    		this.setNextMonday(startWeek);
	    		
	    		for (long idDia : dias) {
	    			if(idDia != 0){
	    				Menu menuElegido= MenuDAO.getMenu(idDia);
	
	    				Reserva reserva=new Reserva(ticket, startWeek.getTime(), menuElegido);
	
	    				if(!this.existReserva(reserva, sede)){
	    					ticket.addReserva(reserva);
	    				}
	    			}
	    			startWeek.add(GregorianCalendar.DATE, 1);
	    			this.controlGregorianCalendar(startWeek);
				}
	    		sessionComensal.setSaldo(sessionComensal.getSaldo() - ticket.getMonto());
	    		TicketDAO.createTicket(ticket);
	    		UserDAO.updateUser(sessionUser);
    		}
	    		
    		return new ModelAndView("redirect:buyTicket");
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
	}
	
	private List<Reserva> getFullDaysReservations(Usuario user, long sede){
		List<Reserva> activeReservations= ReservaDAO.getReservasActivas(user.getId(), sede);
		List<Dia> semana=CartillaDAO.getCartillaActual().getSemana();
		
		System.out.println(activeReservations.size());
		
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
			//while((fullDaysReservation.size() % semana.size()) != 0){
				System.out.println("lalala");
				Reserva mockReservation=new Reserva();
				mockReservation.setFecha(nextMonday.getTime());
				mockReservation.setId((long)0);
				
				fullDaysReservation.add(mockReservation);
				nextMonday.add(GregorianCalendar.DATE, 1);
			}
		}
		
		ListIterator<Dia> semanaIterator=semana.listIterator();
		for (Reserva reserva : fullDaysReservation) {
			if(!semanaIterator.hasNext()){
				semanaIterator=semana.listIterator();
			}
			reserva.setDia(semanaIterator.next());
		}
		
		return fullDaysReservation;
	}
	
	private Double calculatePrice(long[] dias, Double price){
		Integer menus=0;
		for (long l : dias) {
			if(l != 0){
				menus++;
			}
		}
		
		return menus*price;
	}
	
	
	private List<List<Reserva>> cutWeek(List<Reserva> reservas){
		List<List<Reserva>> weeks = new ArrayList<List<Reserva>>();
		
		int numOfWeeks=(int)Math.floor(reservas.size() / 5);
		
		if(numOfWeeks > 1){
			for (int i = 0; i < numOfWeeks; i++) {
				weeks.add(reservas.subList(i*5, (i+1)*5));
			}
		}
		else{
			weeks.add(reservas);
		}
		
		return weeks;
	}
	
	private List<List<Dia>> cutDaysOfWeek(List<Dia> dias){
		List<List<Dia>> weeks = new ArrayList<List<Dia>>();
		
		int numOfWeeks=(int) (Math.ceil(new Double(dias.size()) / 5));
		if(numOfWeeks > 1){
			for (int i = 0; i < numOfWeeks; i++) {
				weeks.add(new ArrayList<Dia>(dias.subList(i*5, ((i+1)*5)-1)));
			}
		}
		else{
			weeks.add(dias);
		}
		
		for (List<Dia> daysInWeeks : weeks) {
			while(daysInWeeks.size() < 5){
				daysInWeeks.add(new Dia());
			}
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
	private Boolean compareDates(Date date1, Date date2){
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

	/**
	 * Retorna si ya existe una reserva para un dia determinado
	 */
	public Boolean existReserva(Reserva reserva, long sede){
		Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
		List<Reserva> reservasActivas= ReservaDAO.getReservasActivas(sessionUser.getId(), sede);
		
		for (Reserva reservaActiva : reservasActivas) {
			if(this.compareDates(reserva.getFecha(), reservaActiva.getFecha())){
				return true;
			}
		}
		
		return false;
	}

	
	
}
