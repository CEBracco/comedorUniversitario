package ttps.controller;

import java.util.ArrayList;
import java.util.HashSet;
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
import ttps.dao.TicketDAO;
import unlp.comedor.Recarga;
import unlp.comedor.Responsable;
import unlp.comedor.Sede;
import unlp.comedor.Usuario;
import ttps.dao.RecargaDAO;
import ttps.dao.ResponsableDAO;
@Controller
public class SedeController {
	@Autowired
    private TicketDAO TicketDAO;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
    private SedeDAO SedeDAO;
	
	@Autowired
    private ResponsableDAO ResponsableDAO;
    
	@Autowired
	private RecargaDAO RecargaDAO;
	
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
    @RequestMapping("sedeResponsable")
    public ModelAndView sedeResponsable(@RequestParam long idSede) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    			Sede sede;
    			
    			sede=SedeDAO.getSede(idSede);
    			ModelAndView vista= new ModelAndView("sedeResponsable", "sedeObject", sede);
    			Responsable responsable = new Responsable();
    			for (Responsable respo : sede.getResponsables()){
					responsable=respo;
				}
    			ArrayList<Responsable> responsableSinSedeAsignada = new ArrayList<Responsable>();
    			List<Responsable> responsableList =ResponsableDAO.getAllResponsables();
    			for (Responsable responsable2 : responsableList) {
					if(responsable2.getSedes().isEmpty()){
						responsableSinSedeAsignada.add(responsable2);
					}
				}
    			
    			
        		vista.addObject("user", sessionUser);
        		vista.addObject("role", sesionRole);
        		vista.addObject("responsableObject",responsable);
        		vista.addObject("responsableList",responsableSinSedeAsignada);
        		return vista;
    		}
    		
    		
    	else{
    		 return new ModelAndView ("redirect:index");
    	}
    }
    @RequestMapping("assignResponsableSede")
    public ModelAndView assignResponsableSede(@RequestParam long idSede,@RequestParam long idResponsable, final RedirectAttributes redirectAttributes) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		if(this.responsableCorrecto(idResponsable) && this.sedeCorrecta(idSede)){
			    	
			    	Sede sede = SedeDAO.getSede(idSede);
			    	if(sede.getResponsables().isEmpty()){
			    		Responsable responsable = ResponsableDAO.getResponsable(idResponsable);
			    		HashSet<Responsable> responsables = new HashSet<Responsable>();
			    		responsables.add(responsable);
			    		sede.setResponsables(responsables);
			    		
			    		SedeDAO.updateSede(sede);
			    		responsable.setSede(SedeDAO.getSede(sede.getId()));
			    		ResponsableDAO.updateResponsable(responsable);
			    		redirectAttributes.addFlashAttribute("msj", "¡responsable asignado con exito!");
			    		return new ModelAndView("redirect:getAllSedes");
			    		}else{
			    			 redirectAttributes.addFlashAttribute("msj", "¡Esta sede tiene un responsable asigndo!");
			    			 return new ModelAndView("redirect:getAllSedes");
			    		}
    		}else{
			    redirectAttributes.addFlashAttribute("msj", "¡Error en los parametros!");
			    return new ModelAndView("redirect:getAllSedes");
			   }
    	}
        return new ModelAndView ("redirect:index");
    }
    @RequestMapping("removeResponsableSede")
    public ModelAndView removeResponsableSede(@RequestParam long idSede,@RequestParam long idResponsable, final RedirectAttributes redirectAttributes) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
			    if(this.responsableCorrecto(idResponsable) && this.sedeCorrecta(idSede)){	
			    	Sede sede = SedeDAO.getSede(idSede);
			    	if(!sede.getResponsables().isEmpty()){
			    		Responsable responsable = ResponsableDAO.getResponsable(idResponsable);
			    		
			    		sede.setResponsables(new HashSet<Responsable>());
			    		SedeDAO.updateSede(sede);
			    		responsable.setSedes(new HashSet<Sede>());
			    		ResponsableDAO.updateResponsable(responsable);
			    		redirectAttributes.addFlashAttribute("msj", "¡Responsable desvinculado con exito!");
			    		return new ModelAndView("redirect:getAllSedes");
			    		}else{
			    			 redirectAttributes.addFlashAttribute("msj", "¡Esta sede no tiene un responsable asigndo!");
			    			 return new ModelAndView("redirect:getAllSedes");
			    		}
			    }else{
			    redirectAttributes.addFlashAttribute("msj", "¡Error en los parametros!");
			    return new ModelAndView("redirect:getAllSedes");
			    }
    	}
        return new ModelAndView ("redirect:index");
    }
    private boolean sedeCorrecta(long sede){
    	HashSet<Long> idSedes = new HashSet<Long>();
    	
    	for (Sede sedes : SedeDAO.getAllSedes()) {
			idSedes.add((sedes.getId()));
			
		}
    	return idSedes.contains(sede);
    	
    }
    private boolean responsableCorrecto(long responsable){
    	HashSet<Long> idResponsable = new HashSet<Long>();
    	
    	for (Responsable responsables : ResponsableDAO.getAllResponsables()) {
			idResponsable.add((responsables.getId()));
			
		}
    	return idResponsable.contains(responsable);
    	
    }
    @RequestMapping("getAllTicketsSedes")
    public ModelAndView getAllTicketsSedes( final RedirectAttributes redirectAttributes) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Responsable")){
    		Responsable responsable = (Responsable)sessionUser;
    		if(!responsable.getSedes().isEmpty()){
    			List<Sede> sedeList = new ArrayList<Sede>(responsable.getSedes());
        		Sede sede = sedeList.get(0);
        		
        		ModelAndView viewListado= new ModelAndView("listTicketSede", "ticketList", TicketDAO.getAllTicketsSede(sede.getId()));
        		viewListado.addObject("sedeObject", sede);
        		viewListado.addObject("user", sessionUser);
        		viewListado.addObject("role", sesionRole);
        		return viewListado;
    		}else{
    			redirectAttributes.addFlashAttribute("msj", "¡este responsable no tiene una sede asignada!");
    			return new ModelAndView("redirect:index");
    		}
    		
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
    @RequestMapping("getAllPagos")
    public ModelAndView getAllPagos() {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Responsable")){
    		Responsable responsable = (Responsable)httpSession.getAttribute("user");
    		
    		List<Recarga> recargaList = new ArrayList<Recarga>();
    		if(!responsable.getSedes().isEmpty()){
	    		List<Sede> sedes = new ArrayList<Sede>(responsable.getSedes());
	    		long idSede= sedes.get(0).getId();
	    		recargaList = RecargaDAO.getAllRecargasSede(idSede);
    		}
    
    		ModelAndView viewListado= new ModelAndView("listPagos", "pagoList", recargaList);
    		viewListado.addObject("user", sessionUser);
    		viewListado.addObject("role", sesionRole);
    		
    		return viewListado;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
}
