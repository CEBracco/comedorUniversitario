package ttps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	public ModelAndView printHello() {
		return new ModelAndView ("hello","mensaje", "Hello Spring MVC");
	}
	
	@RequestMapping(value={"/","index"})
	public ModelAndView printView(){
		return new ModelAndView("index");
	}

	@RequestMapping("/pepe")
	public ModelAndView printPepe(){
		return new ModelAndView("pepe");
	}
	
}
