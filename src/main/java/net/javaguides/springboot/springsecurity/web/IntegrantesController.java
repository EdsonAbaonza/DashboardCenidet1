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
import net.javaguides.springboot.springsecurity.model.Integrantes;
import net.javaguides.springboot.springsecurity.service.AlumnoService;
import net.javaguides.springboot.springsecurity.service.IntegrantesService;

@Controller
public class IntegrantesController {
	@Autowired
	private IntegrantesService IntegrantesService;
	
	@GetMapping("/Integrantes")
	public String viewHomePage(Model model) {
		return findPaginated(1, "clavecuerpo", "asc", model);		
	}

	@GetMapping("/showNewIntegrantesForm")
	public String showNewIntegrantesForm(Model model) {
		// create model attribute to bind form data
		 
		Integrantes integrantes = new Integrantes();
		model.addAttribute("integrantes", integrantes);
		return "new_integrantes";
	}
	

	@PostMapping("/saveIntegrantes")
	public String saveIntegrantes(@ModelAttribute("integrantes") Integrantes integrantes) {
		// save employee to database

		IntegrantesService.saveIntegrantes(integrantes);
		return "redirect:/Integrantes";
	}
	@GetMapping("/showFormForIntegrantesUpdate/{id}")
	public String showFormForIntegrantesUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		Integrantes integrantes = IntegrantesService.getIntegrantesyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("integrantes", integrantes);
		return "update_Integrantes";
	}
	@GetMapping("/deleteIntegrantes/{id}")
	public String deleteIntegrantes(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.IntegrantesService.deleteIntegrantesById(id);
		return "redirect:/Integrantes";
	}
	@GetMapping("/pageIntegrantes/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Integrantes> page = IntegrantesService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Integrantes> listIntegrantes = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listIntegrantes", listIntegrantes);
		return "index_integrantes";
	}
	
}
