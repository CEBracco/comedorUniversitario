package ttps.controller;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ttps.dao.AdministradorDAO;
import ttps.dao.ComensalDAO;
import ttps.dao.SedeDAO;
import ttps.dao.ResponsableDAO;
import unlp.comedor.Administrador;
import unlp.comedor.Responsable;
import unlp.comedor.Sede;
import unlp.comedor.Usuario;
import unlp.comedor.Comensal;

@Controller
public class HelloController {
	
	@Autowired
    private AdministradorDAO AdministradorDAO;
	@Autowired
	private ResponsableDAO ResponsableDAO;
	@Autowired
	private ComensalDAO ComensalDAO;
	@Autowired
	private SedeDAO SedeDAO;
	
	private String defaultUserName="Root";
	private Integer defaultUserDni=12345678;
	private String defaultUserPassword="root";
	
	private String responsableUserName="Responsable1";
	private Integer responsableUserDni=11111111;
	private String responsablePassword="responsable";
	
	private String comensalUserName="Comensal1";
	private Integer comensalUserDni=22222222;
	private String comensalPassword="comensal";
	
	private String nombre="El Bosque";
	private boolean activo =true;
	private String mail="sede_el_bosque@comedor.com";
	private String domicilio="120 y 50";
	private String telefono="22123423";
	
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping(value={"/","index"})
	public ModelAndView printView(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request,HttpServletResponse response){
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
	
	@RequestMapping(value={"menuInformes"})
	public ModelAndView menuInformes(){
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Responsable")){
    		ModelAndView vista= new ModelAndView("reportsView");
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

		if(SedeDAO.getSede(this.nombre)==null){
			Sede sede = new Sede();
			sede.setActivo(this.activo);
			sede.setDomicilio(this.domicilio);
			sede.setNombre(this.nombre);
			sede.setTelefono(this.telefono);
			sede.setMail(this.mail);
			SedeDAO.createSede(sede);
			System.out.println("Se creo la sede test");
		}

		if(AdministradorDAO.getAdministrador(this.defaultUserDni) == null){
    		Administrador defaultUser=new Administrador();
    		
    		defaultUser.setDni(this.defaultUserDni);
    		defaultUser.setNombre(defaultUserName);
    		defaultUser.setPassword(defaultUserPassword);
    		
    		AdministradorDAO.createAdministrador(defaultUser);
    		
    		System.out.println("Se creo el admin default");
		}
		
		if(ResponsableDAO.getResponsable(this.responsableUserDni) == null){
			Responsable responsableUser=new Responsable();
		
			responsableUser.setDni(responsableUserDni);
			responsableUser.setNombre(responsableUserName);
			responsableUser.setPassword(responsablePassword);
			responsableUser.setSede(SedeDAO.getAllSedes().get(0));

			ResponsableDAO.createResponsable(responsableUser);
			
			System.out.println("Se creo el responsable test");
		}
    	
    		
		if(ComensalDAO.getComensal(this.comensalUserDni) == null){	
    		Comensal comensalUser=new Comensal();
    		
    		comensalUser.setDni(comensalUserDni);
    		comensalUser.setNombre(comensalUserName);
    		comensalUser.setPassword(comensalPassword);
    		comensalUser.setApellido("Bracco");
    		comensalUser.setCeliaco(false);
    		comensalUser.setDiabetico(false);
    		comensalUser.setHipertenso(false);
    		comensalUser.setIntolerante(true);
    		comensalUser.setRegular(true);
    		
    		ComensalDAO.createComensal(comensalUser);
    		System.out.println("Se creo el comensal test 1");
		}

		if(ComensalDAO.getComensal(36172193) == null){
			Comensal comensalUser2=new Comensal();
    		
			comensalUser2.setDni(36172193);
			comensalUser2.setApellido("Martin");
			comensalUser2.setNombre("Jony");
			comensalUser2.setPassword("root");
			comensalUser2.setCeliaco(false);
			comensalUser2.setDiabetico(false);
			comensalUser2.setHipertenso(false);
			comensalUser2.setIntolerante(true);
			comensalUser2.setRegular(false);
			
			ComensalDAO.createComensal(comensalUser2);
			System.out.println("Se creo el comensal test 2");
		}
			
		if(ComensalDAO.getComensal(11112222) == null){	
			Comensal comensalUser3=new Comensal();
    		
			comensalUser3.setDni(11112222);
			comensalUser3.setApellido("Cuevas");
			comensalUser3.setNombre("Lucas");
			comensalUser3.setPassword("11112222");
			comensalUser3.setCeliaco(false);
			comensalUser3.setDiabetico(true);
			comensalUser3.setHipertenso(false);
			comensalUser3.setIntolerante(true);
			comensalUser3.setRegular(true);
			
			ComensalDAO.createComensal(comensalUser3);
			System.out.println("Se creo el comensal test 3");
		}

			
		if(ComensalDAO.getComensal(33333333) == null){	
			Comensal comensalUser4=new Comensal();
    		
			comensalUser4.setDni(33333333);
			comensalUser4.setNombre("cristian");
			comensalUser4.setApellido("alvarado");
			comensalUser4.setPassword("albareitor");
			comensalUser4.setCeliaco(false);
			comensalUser4.setDiabetico(false);
			comensalUser4.setHipertenso(false);
			comensalUser4.setIntolerante(true);
			comensalUser4.setRegular(true);
    			
			ComensalDAO.createComensal(comensalUser4);
			System.out.println("Se creo el comensal test 4");
		}
    		
    	
    	
	}

}
