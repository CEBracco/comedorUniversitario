package ttps.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ttps.dao.AdministradorDAO;
import ttps.dao.ComensalDAO;
import ttps.dao.ResponsableDAO;
import unlp.comedor.Administrador;
import unlp.comedor.Comensal;
import unlp.comedor.Responsable;
import unlp.comedor.Usuario;

@Controller
public class LoginController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	AdministradorDAO administradorDAO;
	@Autowired
	ResponsableDAO responsableDAO;
	@Autowired
	ComensalDAO comensalDAO;

	@RequestMapping(value= "login", method = RequestMethod.POST)
	public ModelAndView doLogin(@ModelAttribute("usuario") Usuario usuario, final RedirectAttributes redirectAttributes) {
		Usuario user;
		
		//compruebo si es admin
		user=administradorDAO.getAdministrador(usuario.getDni(), usuario.getPassword());
		if(user != null){
			this.httpSession.setAttribute("user", (Administrador)user);
			this.httpSession.setAttribute("role", "Administrador");
		}
		else{
			//compruebo si es responsable
			user=responsableDAO.getResponsable(usuario.getDni(), usuario.getPassword());
			if(user != null){
				this.httpSession.setAttribute("user", (Responsable)user);
				this.httpSession.setAttribute("role", "Responsable");
			}
			else{
				user=comensalDAO.getComensal(usuario.getDni(), usuario.getPassword());
				if(user != null){
					this.httpSession.setAttribute("user", (Comensal)user);
					this.httpSession.setAttribute("role", "Comensal");
				}
				else{
					redirectAttributes.addFlashAttribute("error", true);
					return new ModelAndView("redirect:index");
				}
			}
		}
		redirectAttributes.addFlashAttribute("error", null);
		return new ModelAndView("redirect:index");
	}
	
	@RequestMapping("goodbye")
	public ModelAndView goodbye(){
		Usuario user=(Usuario)this.httpSession.getAttribute("user");
		if(user != null){
			this.httpSession.setAttribute("user", null);
			this.httpSession.setAttribute("role", null);
		}
		return new ModelAndView("redirect:index");
	}
}