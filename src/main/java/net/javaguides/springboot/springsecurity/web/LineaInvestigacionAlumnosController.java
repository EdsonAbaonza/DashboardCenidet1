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
import net.javaguides.springboot.springsecurity.model.Linea;
import net.javaguides.springboot.springsecurity.model.LineaInvestigacionAlumnos;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.repository.LineaRepository;
import net.javaguides.springboot.springsecurity.service.LineaInvestigacionAlumnosService;

@Controller

public class LineaInvestigacionAlumnosController {
	@Autowired
	private LineaInvestigacionAlumnosService lineaInvestigacionAlumnosService;
	
	@Autowired
	private AlumnoRepository alumnorepo;
	
	@Autowired
	private LineaRepository linearepo;
	
	@GetMapping("/lineaInvestigacionAlumnos")
	public String viewHomePage(Model model) {
		return findPaginated(1, "id", "asc", model);		
	}
	
	@GetMapping("/showNewlineaInvestigacionAlumnosForm")
	public String showNewlineaInvestigacionAlumnosServiceForm(Model model) {
		// create model attribute to bind form data
		List<Alumno> alumno =  alumnorepo.findAll();
		model.addAttribute("alumno", alumno);
		
		List<Linea> linea =  linearepo.findAll();
		model.addAttribute("linea", linea);
		
		LineaInvestigacionAlumnos personaa = new LineaInvestigacionAlumnos();
		model.addAttribute("personaa", personaa);
		return "new_lineaInvestigacionAlumnos";
	}
	
	@PostMapping("/savelineaInvestigacionAlumnos")
	public String savelineaInvestigacionAlumnos(@ModelAttribute("personaa") LineaInvestigacionAlumnos personaa) {
		// save employee to database

		String nivel =personaa.getNivel();
		Boolean status =personaa.isStatus();
		String tipo =personaa.getTipo();
		String fk_alumnos =personaa.getLineaInvestigacionAlumnos().getNombre();
		String fk_alumnosCorreo=personaa.getLineaInvestigacionAlumnos().getCorreo();
		String fk_lineas=personaa.getLinea().getLinea();
		
		lineaInvestigacionAlumnosService.saveLineaInvestigacionAlumnos(personaa);
		long id = personaa.getId();

		return "redirect:/lineaInvestigacionAlumnos";
	}
	@GetMapping("/showFormForlineaInvestigacionAlumnosUpdate/{id}")
	public String showFormForEmpresaUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		LineaInvestigacionAlumnos personaa = lineaInvestigacionAlumnosService.getLineaInvestigacionAlumnosyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("personaa", personaa);
		
		List<Alumno> alumno =  alumnorepo.findAll();
		model.addAttribute("alumno", alumno);
		
		List<Linea> linea =  linearepo.findAll();
		model.addAttribute("linea", linea);
		
		return "update_lineaInvestigacionAlumnos";
	}
	@GetMapping("/deletelineaInvestigacionAlumnos/{id}")
	public String deletePersonaa(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.lineaInvestigacionAlumnosService.deleteLineaInvestigacionAlumnosById(id);
		return "redirect:/lineaInvestigacionAlumnos";
	}
	@GetMapping("/pagelineaInvestigacionAlumnos/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<LineaInvestigacionAlumnos> page = lineaInvestigacionAlumnosService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<LineaInvestigacionAlumnos> listPersonaa = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPersonaas", listPersonaa);
		return "index_lineaInvestigacionAlumnos";
	}
}
