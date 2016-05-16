package ttps.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ttps.dao.CartillaDAO;
import unlp.comedor.Cartilla;
import unlp.comedor.Usuario;

@Controller
public class TicketController {
	
	@Autowired
	private HttpSession httpSession;

	@Autowired
    private CartillaDAO CartillaDAO;
	
	@RequestMapping("buyTicket")
    public ModelAndView buyTicket() {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null){
    		Cartilla cartillaActual=CartillaDAO.getCartillaActual();
    		
    		ModelAndView vista= new ModelAndView("buyTicket");
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sesionRole);
    		vista.addObject("cartilla", cartillaActual);
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
	}
	
	@RequestMapping("saveTicket")
    public ModelAndView saveTicket(@RequestParam long[] dias) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	//String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null){
    		for (long l : dias) {
				System.out.println(l);
			}
    		
    		return new ModelAndView("redirect:buyTicket");
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
	}
	
}
