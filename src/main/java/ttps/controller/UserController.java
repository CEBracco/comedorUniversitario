package ttps.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	        		Responsable respo=responsableDAO.getResponsable(usuario.getId());
	        		respo.setApellido(usuario.getApellido());
	        		respo.setNombre(usuario.getNombre());
	        		respo.setDni(usuario.getDni());
	        		respo.setPassword(usuario.getPassword());
	        		responsableDAO.updateResponsable(respo);
	        		
	        		if(usuario.getRol().equals("admin")){
	        			responsableDAO.toAdministrador(respo);
	        		}
	        		else{
	        			responsableDAO.toResponsable(respo);
	        		}
	        		
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
    		editView.addObject("usuarioObjectRole", usuario.getClass().getSimpleName());
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
    
    @RequestMapping("deleteUsuario")
    public @ResponseBody String deleteUsuario(@RequestParam long id) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		
    		UserDAO.deleteUser(id);
    		
    		return "Usuario eliminado";
    		
    	}else{
    		return "la operacion no ha podido realizarse";
    	}
    }
    
    @RequestMapping("showProfile")
    public ModelAndView showProfile() {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null){
    		ModelAndView presentacion= new ModelAndView("userProfile");
    		presentacion.addObject("user", sessionUser);
    		presentacion.addObject("role", sesionRole);
    		
    		return presentacion;
    	}
    	return new ModelAndView("redirect:index");
    }
    
	@RequestMapping("savePhoto")
    public ModelAndView savePhoto(@RequestParam MultipartFile file) throws IOException, FileNotFoundException {
		//Usuario sessionUser=(Usuario)httpSession.getAttribute("user");

		//Usuario usuario = UserDAO.getUser(sessionUser.getId());
		//usuario.setFoto(route);
		//UserDAO.updateUser(usuario);
		
		if (!file.isEmpty()) {
			 BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
			 File destination = new File("pepe.jpg");
			 ImageIO.write(src, "jpg", destination);
		} 
		
		return new ModelAndView("redirect:showProfile");
    }
}
