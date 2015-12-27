package ttps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ttps.dao.SedeDAO;
import unlp.comedor.Sede;

@Controller
public class SedeController {
	
	@Autowired
    private SedeDAO SedeDAO;

    @RequestMapping("createSedeTest")
    public ModelAndView createSedeTest() {
    	Sede sede= new Sede();
    	sede.setNombre("pepe");
    	sede.setDomicilio("lalala");
    	sede.setMail("lala@alala");
    	
    	SedeDAO.createSede(sede);
    	
    	return new ModelAndView ("hello","mensaje", "Hello Spring MVC");
    }
    
    @RequestMapping("createSede")
    public ModelAndView createSede(@ModelAttribute Sede sede) {
        return new ModelAndView("altaSede");
    }
    
    @RequestMapping("editUsuario")
    public ModelAndView editSede(@RequestParam long id, @ModelAttribute Sede sede) {
        sede = SedeDAO.getSede(id);
        return new ModelAndView("altaSede", "sedeObject", sede);
    }
    
    @RequestMapping("saveSede")
    public ModelAndView saveSede(@ModelAttribute Sede sede) {
    	// Si el id de la Sede 0 entonces se crea la Sede de lo contrario se actualiza 
        if(sede.getId() == 0){ 
            SedeDAO.createSede(sede);
        } else {
            SedeDAO.updateSede(sede);
        }
        return new ModelAndView ("redirect:createSede");
    }
    
    @RequestMapping("getAllSedes")
    public ModelAndView getAllSedes() {
        List<Sede> sedeList = SedeDAO.getAllSedes();
        return new ModelAndView("listSede", "sedeList", sedeList);
    }
    
	@RequestMapping("/altaSede")
	public ModelAndView printAltaSede(){
		return new ModelAndView("altaSede");
	}
	
	@RequestMapping("/listSede")
	public ModelAndView printListSede(){
		return new ModelAndView("listSede");
	}
}
