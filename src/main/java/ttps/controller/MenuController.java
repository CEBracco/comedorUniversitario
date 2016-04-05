package ttps.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ttps.dao.MenuDAO;
import ttps.dao.PlatoDAO;
import unlp.comedor.Menu;
import unlp.comedor.Plato;
import unlp.comedor.Usuario;

@Controller
public class MenuController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private MenuDAO MenuDAO;
	
	@Autowired
	private PlatoDAO PlatoDAO;
	
    
	@RequestMapping("createMenu")
    public ModelAndView createMenu(@ModelAttribute Menu menu) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	List<Plato> platoList= PlatoDAO.getAllPlatos();
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		ModelAndView vista= new ModelAndView("altaMenu");
    		vista.addObject("platoList", platoList);
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sesionRole);
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
    
    @RequestMapping("editMenu")
    public ModelAndView editMenu(@RequestParam long id, @ModelAttribute Menu menu) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	List<Plato> platoList= PlatoDAO.getAllPlatos();
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		menu = MenuDAO.getMenu(id);
    		ModelAndView vista= new ModelAndView("altaMenu", "menuObject", menu);
    		vista.addObject("platoList", platoList);
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sesionRole);
    		vista.addObject("edit", true);
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
    @RequestMapping("saveMenu")
    public ModelAndView saveMenu(@ModelAttribute Menu menu, @RequestParam(required = false) long[] idPlatos ,final RedirectAttributes redirectAttributes) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		if(menu.getId() != null){
	    		menu.setPlatos(new HashSet<Plato>());
	    		//inicializo los atributos(dibetico, celiaco, etc)
	    		initializeAttributes(menu);
	    		
		    	if(idPlatos != null){
		    		for (long idPlato : idPlatos) {
		    			Plato newPlato=PlatoDAO.getPlato(idPlato);
		    			menu.getPlatos().add(newPlato);
		    			setAttributes(newPlato,menu);
					}
		    	}
		    	// Si el id de la Sede 0 entonces se crea la Sede de lo contrario se actualiza 
		    	if(menu.getId() == 0){ 
		    		MenuDAO.createMenu(menu);
		    		redirectAttributes.addFlashAttribute("msj", "¡Menu registrado con exito!");
		    	} else {
		    		MenuDAO.updateMenu(menu);
		    		redirectAttributes.addFlashAttribute("msj", "¡Menu modificado con exito!");
		    	}
    		}
	    	return new ModelAndView ("redirect:getAllMenus");
    	}
        return new ModelAndView ("redirect:index");
    }

    
    @RequestMapping("getAllMenus")
    public ModelAndView getAllMenus() {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		List<Menu> menuList = MenuDAO.getAllMenus();
    		ModelAndView viewListado= new ModelAndView("listMenu", "menuList", menuList);
    		viewListado.addObject("user", sessionUser);
    		viewListado.addObject("role", sesionRole);
    		return viewListado;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
    private void setAttributes(Plato plato, Menu menu){
    	//me fijo si algun atributo esta activado y se lo seteo al menu
		if(plato.getCeliaco()) menu.setCeliaco(true);
		if(plato.getDiabetico()) menu.setDiabetico(true);
		if(plato.getHipertenso()) menu.setHipertenso(true);
		if(plato.getIntolerante()) menu.setIntolerante(true);
		if(plato.getVegetariano()) menu.setVegetariano(true);
    }
    
    private void initializeAttributes(Menu menu){
    	menu.setCeliaco(false);
		menu.setDiabetico(false);
		menu.setHipertenso(false);
		menu.setIntolerante(false);
		menu.setVegetariano(false);
    }
    
    @RequestMapping("deleteMenu")
    public @ResponseBody String deleteMenu(@RequestParam long id) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		
    		MenuDAO.deleteMenu(id);
    		
    		return "menu eliminado";
    		
    	}else{
    		return "la operacion no ha podido realizarse";
    	}
    }
}
