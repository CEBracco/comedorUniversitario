package ttps.controller;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ttps.dao.CartillaDAO;
import ttps.dao.DiaDAO;
import ttps.dao.MenuDAO;
import unlp.comedor.Cartilla;
import unlp.comedor.Dia;
import unlp.comedor.Usuario;

@Controller
public class CartillaController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
    private CartillaDAO CartillaDAO;
	
	@Autowired
    private MenuDAO MenuDAO;
	
	@Autowired
    private DiaDAO DiaDAO;
    
    @RequestMapping("createCartilla")
    public ModelAndView createCartilla(@ModelAttribute Cartilla cartilla) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		ModelAndView vista= new ModelAndView("altaCartilla");
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sesionRole);
    		vista.addObject("menuList", MenuDAO.getAllMenus());
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
    @RequestMapping("editCartilla")
    public ModelAndView editCartilla(@RequestParam long id, @ModelAttribute Cartilla cartilla) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		cartilla = CartillaDAO.getCartilla(id);
    		ModelAndView vista= new ModelAndView("altaCartilla", "cartillaObject", cartilla);
    		vista.addObject("user", sessionUser);
    		vista.addObject("role", sesionRole);
    		vista.addObject("menuList", MenuDAO.getAllMenus());
    		
    		LinkedHashSet<Dia> diaList=toLinkedHashSet(cartilla.getSemana());
    		
    		vista.addObject("diaList", diaList);
    		vista.addObject("edit", true);
    		return vista;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
    
    @RequestMapping("saveCartilla")
    public ModelAndView saveCartilla(@ModelAttribute Cartilla cartilla, @RequestParam(value = "idMenus",required = false) String[] idMenus, final RedirectAttributes redirectAttributes) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
	    	if(cartilla.getId() != null){
	    		// Si el id de la Cartilla 0 entonces se crea la Cartilla de lo contrario se actualiza 
	    		if(cartilla.getId() == 0){ 
	    			cartilla=CartillaDAO.createCartilla(cartilla);
	    			redirectAttributes.addFlashAttribute("msj", "¡Cartilla registrada con exito!");
	    		} else {
	    			redirectAttributes.addFlashAttribute("msj", "¡Cartilla modificada con exito!");
	    		}
	    		
	    		cartilla.setSemana(new LinkedList<Dia>());
	    		if(idMenus != null){
	    			Dia diaActual= new Dia();
	    			for (String idMenu : idMenus) {
						if(isNumeric(idMenu)){
							diaActual.getMenus().add(MenuDAO.getMenu(Integer.parseInt(idMenu)));
						}
						else{
							diaActual.setCartilla(cartilla);
							diaActual= DiaDAO.createDia(diaActual);
							cartilla.getSemana().add(diaActual);
							diaActual= new Dia();
						}
					}
	    		}
	    		CartillaDAO.updateCartilla(cartilla);
	    	}
	    	return new ModelAndView ("redirect:getAllCartillas");
    	}
        return new ModelAndView ("redirect:index");
    }
    
    @RequestMapping("getAllCartillas")
    public ModelAndView getAllCartillas() {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		List<Cartilla> cartillaList = CartillaDAO.getAllCartillas();
    		ModelAndView viewListado= new ModelAndView("listCartilla", "cartillaList", cartillaList);
    		viewListado.addObject("user", sessionUser);
    		viewListado.addObject("role", sesionRole);
    		return viewListado;
    	}
    	else{
    		return new ModelAndView("redirect:index");
    	}
    }
	
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
    
    private boolean isNumeric(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }

        return true;
    }
    
    private LinkedHashSet<Dia> toLinkedHashSet(List<Dia> list){
    	Comparator<Dia> c=new Comparator<Dia>() {
			
			@Override
			public int compare(Dia o1, Dia o2) {
				return (int)(o1.getId() - o2.getId());
			}
		};
    	
		list.sort(c);
    	
    	LinkedHashSet<Dia> set=new LinkedHashSet<Dia>();
    	for (Dia dia : list) {
			set.add(dia);
		}
    	return set;
    }
    
    @RequestMapping("deleteCartilla")
    public @ResponseBody String deleteCartilla(@RequestParam long id) {
    	Usuario sessionUser=(Usuario)httpSession.getAttribute("user");
    	String sesionRole=(String)httpSession.getAttribute("role");
    	if(sessionUser != null && sesionRole.equals("Administrador")){
    		
    		CartillaDAO.deleteCartilla(id);
    		
    		return "cartilla eliminado";
    		
    	}else{
    		return "la operacion no ha podido realizarse";
    	}
    }
}
