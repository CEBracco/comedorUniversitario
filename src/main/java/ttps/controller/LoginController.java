package ttps.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ttps.dao.UserDAO;
import unlp.comedor.Usuario;

@Controller
public class LoginController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	UserDAO UserDAO;

	@RequestMapping(value= "login", method = RequestMethod.POST)
	public ModelAndView doLogin(@ModelAttribute("usuario") Usuario usuario) {
		Usuario user=UserDAO.getUser(usuario.getDni(), usuario.getPassword());
		if(user != null){
			this.httpSession.setAttribute("user", user);
		}
		return new ModelAndView("redirect:index");
		
	}
	
	@RequestMapping("goodbye")
	public ModelAndView goodbye(){
		Usuario user=(Usuario)this.httpSession.getAttribute("user");
		if(user != null){
			this.httpSession.setAttribute("user", null);
		}
		return new ModelAndView("redirect:index");
	}
}