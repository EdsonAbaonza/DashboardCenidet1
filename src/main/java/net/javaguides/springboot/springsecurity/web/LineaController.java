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

import net.javaguides.springboot.springsecurity.model.Linea;
import net.javaguides.springboot.springsecurity.service.LineaService;

@Controller
public class LineaController {

	@Autowired
	private LineaService lineaervice;
	
	@GetMapping("/Linea")
	public String viewHomePage(Model model) {
		return findPaginated(1, "linea", "asc", model);		
	}
	
	

	@GetMapping("/showNewLineaForm")
	public String showNewLineaForm(Model model) {
		// create model attribute to bind form data
		 
		Linea personaa = new Linea();
		model.addAttribute("personaa", personaa);
		return "New_linea";
	}
	
	@PostMapping("/saveLinea")
	public String saveLinea(@ModelAttribute("personaa") Linea personaa) {
		// save employee to database

		lineaervice.saveLinea(personaa);
		return "redirect:/Linea";
	}
	
	@GetMapping("/showFormForLineaUpdate/{id}")
	public String showFormForLineaUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		Linea personaa = lineaervice.getLineayId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("personaa", personaa);
		return "update_Linea";
	}
	@GetMapping("/deleteLinea/{id}")
	public String deletePersonaa(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.lineaervice.deleteLineaById(id);
		return "redirect:/Linea";
	}
	
	@GetMapping("/pageLinea/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Linea> page = lineaervice.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Linea> listPersonaa = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPersonaas", listPersonaa);
		return "index_Linea";
	}
	
}
