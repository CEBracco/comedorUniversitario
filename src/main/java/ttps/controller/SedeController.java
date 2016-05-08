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

import ttps.dao.SedeDAO;
import unlp.comedor.Sede;
import unlp.comedor.Usuario;

@Controller
public class SedeController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
    private SedeDAO SedeDAO;
    
    @RequestMapping("createSede")
    public ModelAndView createSede(@ModelAttribute Sede sede) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		ModelAndView vista= new ModelAndView("altaSede");
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sesionRole);
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
    @RequestMapping("editSede")
    public ModelAndView editSede(@RequestParam long id, @ModelAttribute Sede sede) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		sede = SedeDAO.getSede(id);
    		ModelAndView vista= new ModelAndView("altaSede", "sedeObject", sede);
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sesionRole);
    		vista.addObject("edit", true);
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
    @RequestMapping("saveSede")
    public ModelAndView saveSede(@ModelAttribute Sede sede, final RedirectAttributes redirectAttributes) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
	    	if(sede.getId() != null){
		    	// Si el id de la Sede 0 entonces se crea la Sede de lo contrario se actualiza 
		        if(sede.getId() == 0){ 
		            SedeDAO.createSede(sede);
		            redirectAttributes.addFlashAttribute("msj", "¡Sede registrada con exito!");
		        } else {
		            SedeDAO.updateSede(sede);
		            redirectAttributes.addFlashAttribute("msj", "¡Sede modificada con exito!");
		        }
	    	}
	    	return new ModelAndView ("redirect:getAllSedes");
    	}
        return new ModelAndView ("redirect:index");
    }
    
    @RequestMapping("getAllSedes")
    public ModelAndView getAllSedes() {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		List<Sede> sedeList = SedeDAO.getAllSedes();
    		ModelAndView viewListado= new ModelAndView("listSede", "sedeList", sedeList);
    		viewListado.addObject("user", sessionUser);
    		viewListado.addObject("role", sesionRole);
    		return viewListado;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
	
    @RequestMapping("deleteSede")
    public @ResponseBody String deleteSede(@RequestParam long id) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		
    		if(SedeDAO.getSede(id).getResponsables().isEmpty()){
    			SedeDAO.deleteSede(id);
    			return "Sede eliminada";
    		}
    		else{
    			return "La sede no debe poseer responsables asignados";
    		}
    		
    	}else{
    		return "No tiene permisos para realizar esta accion!";
    	}
    }
}
