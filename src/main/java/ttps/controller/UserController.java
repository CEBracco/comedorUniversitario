package ttps.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import unlp.comedor.*;
import ttps.dao.*;
@Controller
public class UserController {
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
    private UserDAO UserDAO;
	@Autowired
	private ResponsableDAO responsableDAO;
	@Autowired
	private AdministradorDAO administradorDAO;
	@Autowired
	private ComensalDAO comensalDAO;
	
	@RequestMapping("createUsuario")
	public ModelAndView createUsuario(@ModelAttribute Usuario usuario) {
		Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		ModelAndView viewForm= new ModelAndView("altaUser");
    		
    		viewForm.addObject("user", sessionUser);
    		viewForm.addObject("role", sesionRole);
    		
    		return viewForm;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
	}
	
	
	@RequestMapping("saveUsuario")
    public ModelAndView saveUsuario(@ModelAttribute Usuario usuario, final RedirectAttributes redirectAttributes) {
		Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
	    	if(usuario.getId() != null){
		    	// Si el id del Usuario 0 entonces se crea el Usuario de lo contrario se actualiza 
		        if(usuario.getId() == 0){
		        	if(usuario.getRol().equals("admin")){
		        		Administrador admin = new Administrador();
		        		admin.setApellido(usuario.getApellido());
		        		admin.setNombre(usuario.getNombre());
		        		admin.setDni(usuario.getDni());
		        		admin.setPassword(usuario.getPassword());
		        		administradorDAO.createAdministrador(admin);
		        	}
		        	else{
		        		Responsable respo = new Responsable();
		        		respo.setApellido(usuario.getApellido());
		        		respo.setNombre(usuario.getNombre());
		        		respo.setDni(usuario.getDni());
		        		respo.setPassword(usuario.getPassword());
		        		responsableDAO.createResponsable(respo);
		        	}
		        }
		        else {
		        	UserDAO.updateUser(usuario);
		        }
		        redirectAttributes.addFlashAttribute("msj", "¡Usuario registrado con exito!");
	    	}
	    	return new ModelAndView("redirect:getAllUsuarios");
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
	 
	@RequestMapping("editUsuario")
    public ModelAndView editUsuario(@RequestParam long id, @ModelAttribute Usuario usuario) {
		Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		usuario = UserDAO.getUser(id);
    		ModelAndView editView= new ModelAndView("altaUser", "usuarioObject", usuario);
	        editView.addObject("user", sessionUser);
    		editView.addObject("role", sesionRole);
    		editView.addObject("edit", true);
    		return editView;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
	
	@RequestMapping("getAllUsuarios")
    public ModelAndView getAllUsuarios() {
		Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
			List<Administrador> adminList = administradorDAO.getAllAdministradores();
	        List<Responsable> respList = responsableDAO.getAllResponsables();
	        List<Comensal> comenList = comensalDAO.getAllComensales();
	        ModelAndView presentacion= new ModelAndView("listUser");
	        presentacion.addObject("adminList", adminList);
	        presentacion.addObject("respList", respList);
	        presentacion.addObject("comenList",comenList);
	        
	        presentacion.addObject("user", sessionUser);
    		presentacion.addObject("role", sesionRole);
	        
    		return presentacion;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
	
}
