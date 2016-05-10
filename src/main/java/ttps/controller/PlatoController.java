package ttps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ttps.dao.PlatoDAO;
import unlp.comedor.Plato;
import unlp.comedor.Usuario;

@Controller
public class PlatoController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private PlatoDAO PlatoDAO;
	
	@RequestMapping("createPlato")
    public ModelAndView createPlato(@ModelAttribute Plato plato) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		ModelAndView vista= new ModelAndView("altaPlato");
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sesionRole);
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
	
	
	
    @RequestMapping("editPlato")
    public ModelAndView editPlato(@RequestParam long id, @ModelAttribute Plato plato) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		plato = PlatoDAO.getPlato(id);
    		ModelAndView vista= new ModelAndView("altaPlato", "platoObject", plato);
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sesionRole);
    		vista.addObject("edit", true);
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
    @RequestMapping("savePlato")
    public ModelAndView savePlato(@ModelAttribute Plato plato, final RedirectAttributes redirectAttributes) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
	    	if(plato.getId() != null){
		    	// Si el id de la Sede 0 entonces se crea la Sede de lo contrario se actualiza 
		        if(plato.getId() == 0){ 
		            PlatoDAO.createPlato(plato);
		            redirectAttributes.addFlashAttribute("msj", "¡Plato registrado con exito!");
		        } else {
		            PlatoDAO.updatePlato(plato);
		            redirectAttributes.addFlashAttribute("msj", "¡Plato modificado con exito!");
		        }
	    	}
	    	return new ModelAndView ("redirect:getAllPlatos");
    	}
        return new ModelAndView ("redirect:index");
    }
    
    @RequestMapping("getAllPlatos")
    public ModelAndView getAllPlatos() {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		List<Plato> platoList = PlatoDAO.getAllPlatos();
    		ModelAndView viewListado= new ModelAndView("listPlato", "platoList", platoList);
    		viewListado.addObject("user", sessionUser);
    		viewListado.addObject("role", sesionRole);
    		return viewListado;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
    @RequestMapping("deletePlato")
    public @ResponseBody String deletePlato(@RequestParam long id) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		
    		PlatoDAO.deletePlato(id);
    		
    		return "plato eliminado";
    		
    	}else{
    		return "la operacion no ha podido realizarse";
    	}
    }
}
