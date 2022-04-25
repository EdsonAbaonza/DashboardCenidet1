package net.javaguides.springboot.springsecurity.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Instituciones;
import net.javaguides.springboot.springsecurity.service.InstitucionService;


@Controller
public class InstitucionesController {

	@Autowired
	private InstitucionService institucionervice;
	
	@GetMapping("/Instituciones")
	public String viewHomePage(Model model) {
		return findPaginated(1, "id", "asc", model);		
	}

	@GetMapping("/showNewInstitucionesForm")
	public String showNewInstitucionesForm(Model model) {
		// create model attribute to bind form data
		 
		Instituciones personaa = new Instituciones();
		model.addAttribute("personaa", personaa);
		return "new_instituciones";
	}
	@PostMapping("/saveInstituciones")
	public String saveInstituciones(@ModelAttribute("personaa") Instituciones personaa) {
		// save employee to database

		institucionervice.saveInstituciones(personaa);
		return "redirect:/Instituciones";
	}
	@GetMapping("/showFormForInstitucionesUpdate/{id}")
	public String showFormForInstitucionesUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		Instituciones personaa = institucionervice.getInstitucionesId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("personaa", personaa);
		return "update_Instituciones";
	}
	
	@GetMapping("/deleteInstituciones/{id}")
	public String deleteInstituciones(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.institucionervice.deleteInstitucionesById(id);
		return "redirect:/Instituciones";
	}
	
	@GetMapping("/pageInstituciones{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Instituciones> page = institucionervice.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Instituciones> listPersonaa = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPersonaas", listPersonaa);
		return "index_Instituciones";
	}
	
}
