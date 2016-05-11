package ttps.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import unlp.comedor.Plato;
import unlp.comedor.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import ttps.dao.SedeDAO;
import ttps.dao.SugerenciaDAO;
import unlp.comedor.Plato;
import unlp.comedor.Sede;
import unlp.comedor.Sugerencia;
import unlp.comedor.Usuario;

@Controller
public class SugerenciaController {

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private SugerenciaDAO Sugerencia;
	
	@Autowired
	private SedeDAO Sede;
	
	@RequestMapping("createSugerencia")
    public ModelAndView createSugerencia(@ModelAttribute Plato plato) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Responsable")){
//    		ModelAndView vista= new ModelAndView("altaSugerencia");
    		
    		List<Sede> sedeList = Sede.getAllSedes();
    		
    		ModelAndView vista= new ModelAndView("altaSugerencia", "sedeList", sedeList);
    		vista.addObject("sugerencia", new Sugerencia());
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sesionRole);
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
	
	@RequestMapping("saveSugerencia")
    public ModelAndView saveSugerencia(@ModelAttribute Sugerencia sugerencia, @RequestParam(required = false) long idSedes,final RedirectAttributes redirectAttributes) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Responsable")){
	    	if(sugerencia.getId() != null){
		    	// Si el id de la Sede 0 entonces se crea la Sede de lo contrario se actualiza 
		        if(sugerencia.getId() == 0){
		        	if(idSedes != 0){
		        	sugerencia.setSede(Sede.getSede(idSedes));
		        	}
		        	sugerencia.setUsuario(sessionUser);
		        	
		        
		            Sugerencia.createSugerencia(sugerencia);
		            redirectAttributes.addFlashAttribute("msj", "¡Sugerencia registrada con exito!");
		        } else {
		            Sugerencia.updateSugerencia(sugerencia);
		            redirectAttributes.addFlashAttribute("msj", "¡Sede modificada con exito!");
		        }
	    	}
	    	return new ModelAndView ("redirect:getAllSedes");
    	}
        return new ModelAndView ("redirect:index");
    }
	
	   @RequestMapping("getAllSugerencias")
	    public ModelAndView getAllSugerencias() {
	    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
	    	String sesionRole=(String)httpSession.getAttribute("role");
	    	if(sessionUser != null && sesionRole.equals("Administrador")){
	    		List<Sugerencia> sugerenciaList = Sugerencia.getAllSugerencias();
	    		
	    		ModelAndView viewListado= new ModelAndView("listSugerencia", "sugerenciaList", sugerenciaList);
	    		viewListado.addObject("user", sessionUser);
	    		viewListado.addObject("role", sesionRole);
	    		return viewListado;
	    	}
	    	else{
	    		return new ModelAndView("redirect:index");
	    	}
	    }
}
