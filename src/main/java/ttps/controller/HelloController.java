package ttps.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ttps.dao.AdministradorDAO;
import unlp.comedor.Administrador;
import unlp.comedor.Usuario;

@Controller
public class HelloController {
	
	@Autowired
    private AdministradorDAO AdministradorDAO;
	
	private String defaultUserName="Root";
	private Integer defaultUserDni=12345678;
	private String defaultUserPassword="root";
	
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping(value={"/","index"})
	public ModelAndView printView(@ModelAttribute("usuario") Usuario usuario){
		Usuario user=(Usuario) httpSession.getAttribute("user");
		ModelAndView view= new ModelAndView("index");
		if(user != null){
			view.addObject("user", user);
			view.addObject("role", (String) httpSession.getAttribute("role"));
		}
		return view;
	}
	
	@RequestMapping(value={"menuCartilla"})
	public ModelAndView menuCartilla(){
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		ModelAndView vista= new ModelAndView("cartillaView");
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sesionRole);
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
	}
	
	@PostConstruct
	private void initialize(){
		if(AdministradorDAO.getAdministrador(this.defaultUserDni, this.defaultUserPassword) == null){
    		Administrador defaultUser=new Administrador();
    		defaultUser.setDni(this.defaultUserDni);
    		defaultUser.setNombre(defaultUserName);
    		defaultUser.setPassword(defaultUserPassword);
    		
    		AdministradorDAO.createAdministrador(defaultUser);
    		System.out.println("Se creo el usuario default");
    	}
	}

}
