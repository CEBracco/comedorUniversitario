package ttps.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import unlp.comedor.Usuario;

@Controller
public class HelloController {
	
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping("/hello")
	public ModelAndView printHello() {
		return new ModelAndView ("hello","mensaje", "Hello Spring MVC");
	}
	
	@RequestMapping(value={"/","index"})
	public ModelAndView printView(@ModelAttribute("usuario") Usuario usuario){
		Usuario user=(Usuario) httpSession.getAttribute("user");
		if(user != null){
			return new ModelAndView("index", "user", user);
		}
		else{
			return new ModelAndView("index");
		}
	}

	@RequestMapping("/pepe")
	public ModelAndView printPepe(){
		return new ModelAndView("pepe");
	}
	
}
