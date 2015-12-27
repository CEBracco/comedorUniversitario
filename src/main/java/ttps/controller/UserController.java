package ttps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import unlp.comedor.Usuario;
import ttps.dao.*;
@Controller
public class UserController {
	@Autowired
    private UserDAO UserDAO;

	@RequestMapping("createUsuario")
	public ModelAndView createUsuario(@ModelAttribute Usuario usuario) {
	    return new ModelAndView("altaUser");
	    }

	@RequestMapping("/listUser")
	public ModelAndView printListUser(){
		return new ModelAndView("listUser");
	}
	
	
	 @RequestMapping("saveUsuario")
	    public ModelAndView saveUsuario(@ModelAttribute Usuario usuario) {
	    	// Si el id del Usuario 0 entonces se crea el Usuario de lo contrario se actualiza 
	        if(usuario.getId() == 0){ 
	            UserDAO.createUser(usuario);
	        } else {
	            UserDAO.updateUser(usuario);
	        }
	        return new ModelAndView("redirect:createUsuario");
	    }
	@RequestMapping("editUsuario")
    public ModelAndView editUsuario(@RequestParam long id, @ModelAttribute Usuario usuario) {
        usuario = UserDAO.getUser(id);
        return new ModelAndView("altaUser", "usuarioObject", usuario);
    }
    
	
}
