package net.javaguides.springboot.springsecurity.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Departamento;
import net.javaguides.springboot.springsecurity.model.Linea;
import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.model.Tesis;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.repository.DepartamentoRepository;
import net.javaguides.springboot.springsecurity.repository.LineaRepository;
import net.javaguides.springboot.springsecurity.repository.ProfesorRepository;
import net.javaguides.springboot.springsecurity.service.TesisService;

@Controller
public class TesisController {
	@Autowired
	private TesisService tesisService;
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private LineaRepository lineaRepository;
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@GetMapping("/Tesis")
	public String viewHomePage(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		model.addAttribute("usernames", username);
		return findPaginated(1, "titulo", "asc", model);		
	}

	@GetMapping("/showNewTesisForm")
	public String showNewTesisForm(Model model) {
		// create model attribute to bind form data
		List<Profesor> profesor =  profesorRepository.findAll();
		model.addAttribute("profesor", profesor);
		
		List<Departamento> departamento =  departamentoRepository.findAll();
		model.addAttribute("departamento", departamento);
		
		List<Linea> linea =  lineaRepository.findAll();
		model.addAttribute("linea", linea);
		
		//List<Alumno> alumno =  alumnoRepository.findAll();
		//model.addAttribute("alumno", alumno);
		
		Tesis tesis = new Tesis();
		model.addAttribute("tesis", tesis);
		return "new_Tesis";
	}
	

	@PostMapping("/saveTesis")
	public String saveTesis(@ModelAttribute("tesis") Tesis tesis) {
		tesis.setDirector(tesis.getProfesor().getNombre()+ ' ' + tesis.getProfesor().getApellido_paterno()+ ' ' + tesis.getProfesor().getApellido_materno());
		tesisService.saveTesis(tesis);
		return "redirect:/Tesis";
	}
	@GetMapping("/showFormForTesisUpdate/{id}")
	public String showFormForTesisUpdate(@PathVariable ( value = "id") long id, Model model) {
		// get employee from the service
		
				List<Profesor> profesor =  profesorRepository.findAll();
				model.addAttribute("profesor", profesor);
				
				List<Departamento> departamento =  departamentoRepository.findAll();
				model.addAttribute("departamento", departamento);
				
				List<Linea> linea =  lineaRepository.findAll();
				model.addAttribute("linea", linea);
				
				Tesis tesis = tesisService.getTesisyId(id);
				
		// set employee as a model attribute to pre-populate the form
			model.addAttribute("tesis", tesis);
			
		tesisService.saveTesis(tesis);
		//long id = tesis.getId();
		
		
		return "update_Tesis";
	}
	@GetMapping("/deleteTesis/{id}")
	public String deleteTesis(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.tesisService.deleteTesisById(id);
		return "redirect:/Tesis";
	}
	@GetMapping("/pageTesis/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Tesis> page = tesisService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Tesis> listTesis = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listTesis", listTesis);
		return "index_Tesis";
	}
}
