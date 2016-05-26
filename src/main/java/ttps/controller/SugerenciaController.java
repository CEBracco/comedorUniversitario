package ttps.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import unlp.comedor.Plato;
import unlp.comedor.Responsable;
import unlp.comedor.Usuario;
import unlp.comedor.Comensal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestParam;

import ttps.dao.ComensalDAO;
import ttps.dao.ResponsableDAO;
import ttps.dao.SedeDAO;
import ttps.dao.SugerenciaDAO;

import unlp.comedor.Sede;
import unlp.comedor.Sugerencia;

@Controller
public class SugerenciaController {
	@Autowired
	private ComensalDAO ComensalDao;
	
	@Autowired
	private ResponsableDAO ResponsableDao;
	
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
    	if(sessionUser != null && sesionRole.equals("Comensal")){
  		
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
    public ModelAndView saveSugerencia(@ModelAttribute Sugerencia sugerencia, @RequestParam(required = false) String idSedes,final RedirectAttributes redirectAttributes) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Comensal")){
	    	if(sugerencia.getId() != null){
		    	// Si el id de la Sede 0 entonces se crea la Sede de lo contrario se actualiza 
		        if(sugerencia.getId() == 0){
		        	
		        	if(idSedes != null && this.sedeCorrecta(idSedes)){
		        		if(idSedes.equals("ALL")){
		        				//a todas las sedes
					        	List<Sede> sedes = Sede.getAllSedes();
					        	for (Sede sede : sedes) {
									sugerencia.setSede(sede);
								}
			//		        	Sede sede = Sede.getSede(((long)Long.parseLong(idSedes)));
			//		        	sede.setSugerencia(sugerencia);
					        	
		        		}else{
		        			//a ninguna de las sedes
		        			if(idSedes.equals("NONE")){
		        				
		        			}else{
		        				//solo una sede
		        				sugerencia.setSede(Sede.getSede(((long)Long.parseLong(idSedes))));
		        			}
		        			
		        			
		        		}
		        		
					        	
		        	}
		        	sugerencia.setUsuario(sessionUser);
		        	
		        
		            Sugerencia.createSugerencia(sugerencia);
		            redirectAttributes.addFlashAttribute("msj", "¡sugerencia enviada con exito!");
		        } else {
		            
		            redirectAttributes.addFlashAttribute("msj", "¡sugerencia modificada con exito!");
		            return new ModelAndView ("redirect:getAllSugerencias");
		        }
	    	}
	    	return new ModelAndView ("redirect:getAllSugerencias");
    	}
        return new ModelAndView ("redirect:index");
    }
	
	   @RequestMapping("getAllSugerencias")
	    public ModelAndView getAllSugerencias() {
	    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
	    	String sesionRole=(String)httpSession.getAttribute("role");
	    	if(sessionUser != null ){
	    		
	    		
	    		ModelAndView viewListado= new ModelAndView("listSugerencia");
	    		viewListado.addObject("user", sessionUser);
	    		viewListado.addObject("role", sesionRole);
	    		
		    	if(sesionRole.equals("Administrador")){
		    		
		    		 viewListado.addObject("sugerenciaList",Sugerencia.getAllSugerencias());
		    		return viewListado;
		    	}else{
		    		if(sesionRole.equals("Responsable")){
		    			
		    		 Responsable responsable=ResponsableDao.getResponsable(((Usuario)httpSession.getAttribute("user")).getId());
		    		 ArrayList<Sugerencia> sugerSede = new ArrayList<Sugerencia>();
		    		 HashSet<Sede> listadoSede=new HashSet<Sede>(responsable.getSedes());
		    		 for (Sede sede : listadoSede) {
						for (Sugerencia sugerencia : sede.getSugerencias()) {
							sugerSede.add(sugerencia);
						}
					}
		    		
		    		 viewListado.addObject("sugerenciaList",sugerSede);
		    			return viewListado;
		    		}else{
		    				Comensal comensal=ComensalDao.getComensal(((Usuario)httpSession.getAttribute("user")).getId());
		    				viewListado.addObject("sugerenciaList",comensal.getSugerencias());
		    				return viewListado;
		    			
		    		}
		    	}
	    		
	    	}else{
				//sesion vacia
				return new ModelAndView("redirect:index");
			}
	    }

	   
	    @RequestMapping("verSugerencia")
	    public ModelAndView verSugerencia(@RequestParam long id) {
	    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
	    	String sesionRole=(String)httpSession.getAttribute("role");
	    	if(sessionUser != null ){
	    		Sugerencia sugerencia = Sugerencia.getSugerencia(id);
	    		
	    		
	    		ModelAndView viewListado= new ModelAndView("verSugerencia", "sugerencia", sugerencia);
	    		viewListado.addObject("user", sessionUser);
	    		viewListado.addObject("role", sesionRole);
	    		return viewListado;
	    	}
	    	else{
	    		return new ModelAndView("redirect:index");
	    	}
	    }
	    @RequestMapping("saveRespuesta")
	    public ModelAndView saveRespuesta(@ModelAttribute Sugerencia sugerencia,final RedirectAttributes redirectAttributes) {
	    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
	    	String sesionRole=(String)httpSession.getAttribute("role");
	    	if(sessionUser != null && this.saveRespuesta(sesionRole)){
	    		Sugerencia sugerenciaModificar = Sugerencia.getSugerencia(sugerencia.getId());
	    		sugerenciaModificar.setRespuesta(sugerencia.getRespuesta());
	    		Sugerencia.updateSugerencia(sugerenciaModificar);
	    		
	    		
	    		ModelAndView viewListado= new ModelAndView("verSugerencia", "sugerencia", sugerenciaModificar);
	    		redirectAttributes.addFlashAttribute("msj", "¡Respuesta enviada con exito!");
	    		viewListado.addObject("user", sessionUser);
	    		viewListado.addObject("role", sesionRole);
	    		return viewListado;
	    	}
	    	else{
	    		return new ModelAndView("redirect:index");
	    	}
	    }
	    private boolean sedeCorrecta(String sede){
	    	HashSet<String> elementosPermitidos = new HashSet<String>();
	    	elementosPermitidos.add("ALL");
	    	elementosPermitidos.add("NONE");
	    	for (Sede sedes : Sede.getAllSedes()) {
				elementosPermitidos.add((sedes.getId()).toString());
				
			}
	    	return elementosPermitidos.contains(sede);
	    	
	    }
	    private boolean saveRespuesta(String sessionRole){
	    	return (sessionRole.equals("Administrador")|| sessionRole.equals("Responsable"));
	    }
}
