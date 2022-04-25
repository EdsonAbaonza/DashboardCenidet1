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
import net.javaguides.springboot.springsecurity.model.LineaInvestigacionProfesores;
import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.repository.LineaRepository;
import net.javaguides.springboot.springsecurity.repository.ProfesorRepository;
import net.javaguides.springboot.springsecurity.service.LineasInvestigacionProfesoresService;

@Controller
public class LineasInvestigacionProfesorController {
	@Autowired
	private LineasInvestigacionProfesoresService lineasInvestigacionProfesoresService;
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private LineaRepository linearepo;
	
	@GetMapping("/lineaInvestigacionProfesores")
	public String viewHomePage(Model model) {
		return findPaginated(1, "id", "asc", model);		
	}
	
	@GetMapping("/showNewlineaInvestigacionProfesoresForm")
	public String showNewlineaInvestigacionProfesoresForm(Model model) {
		// create model attribute to bind form data
		List<Profesor> profesor =  profesorRepository.findAll();
		model.addAttribute("profesor", profesor);
		
		List<Linea> linea =  linearepo.findAll();
		model.addAttribute("linea", linea);
		
	
		
		LineaInvestigacionProfesores personaa = new LineaInvestigacionProfesores();
		model.addAttribute("personaa", personaa);
		return "new_lineaInvestigacionProfesores";
	}
	
	@PostMapping("/savelineaInvestigacionProfesores")
	public String savelineaInvestigacionProfesores(@ModelAttribute("personaa") LineaInvestigacionProfesores personaa) {
		// save employee to database

		Boolean status =personaa.isStatus();
		String tipo =personaa.getTipo();
		String fk_profesores =personaa.getProfesor().getNombre();
		String fk_profesoresCorreo=personaa.getProfesor().getCorreo();
		String fk_lineasProfesor=personaa.getLineaprofesor().getLinea();

	
		
		lineasInvestigacionProfesoresService.saveLineaInvestigacionProfesores(personaa);
		long id = personaa.getId();

		return "redirect:/lineaInvestigacionProfesores";
	}
	@GetMapping("/showFormForlineaInvestigacionProfesoresUpdate/{id}")
	public String showFormForlineaInvestigacionProfesoresUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		LineaInvestigacionProfesores personaa = lineasInvestigacionProfesoresService.getLineaInvestigacionProfesoresyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("personaa", personaa);
		
		List<Profesor> profesor =  profesorRepository.findAll();
		model.addAttribute("profesor", profesor);
		
		List<Linea> linea =  linearepo.findAll();
		model.addAttribute("linea", linea);
		
	
		
		return "update_lineaInvestigacionProfesores";
	}
	
	@GetMapping("/deletelineaInvestigacionProfesores/{id}")
	public String deletePersonaa(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.lineasInvestigacionProfesoresService.deleteLineaInvestigacionProfesoresById(id);
		return "redirect:/lineaInvestigacionProfesores";
	}
	@GetMapping("/pagelineaInvestigacionProfesores/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<LineaInvestigacionProfesores> page = lineasInvestigacionProfesoresService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<LineaInvestigacionProfesores> listPersonaa = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	
		model.addAttribute("listPersonaas", listPersonaa);
		return "index_lineaInvestigacionProfesores";
	}
}
