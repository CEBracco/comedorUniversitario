package ttps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	@RequestMapping("/altaUser")
	public ModelAndView printAltaUser(){
		return new ModelAndView("altaUser");
	}

	@RequestMapping("/listUser")
	public ModelAndView printListUser(){
		return new ModelAndView("listUser");
	}
	
}
