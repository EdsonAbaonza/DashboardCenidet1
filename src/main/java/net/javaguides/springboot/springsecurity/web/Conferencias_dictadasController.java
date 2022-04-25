package net.javaguides.springboot.springsecurity.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import net.javaguides.springboot.springsecurity.model.Conferencias_dictadas;
import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.repository.ProfesorRepository;
import net.javaguides.springboot.springsecurity.service.AlumnoService;
import net.javaguides.springboot.springsecurity.service.Conferencias_dictadasService;

@Controller
public class Conferencias_dictadasController {
	@Autowired
	private Conferencias_dictadasService Conferencias_dictadasService;
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@GetMapping("/Conferencias_dictadas")
	public String viewHomePage(Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		model.addAttribute("usernames", username);
		
		return findPaginated(1, "tituloconferencia", "asc", model);		
	}

	@GetMapping("/showNewConferenciaDictadaForm")
	public String showNewConferenciaDictadaForm(Model model) {
		// create model attribute to bind form data
		
		List<Profesor> profesor =  profesorRepository.findAll();
		model.addAttribute("profesor", profesor);
		
		Conferencias_dictadas conferencias_dictadas = new Conferencias_dictadas();
		model.addAttribute("conferencias_dictadas", conferencias_dictadas);
		return "new_Conferencias_dictadas";
	}
	

	@PostMapping("/saveConferenciaDictada")
	public String saveConferenciaDictada(@ModelAttribute("conferencias_dictadas") Conferencias_dictadas conferencias_dictadas) {
		// save employee to database

		Conferencias_dictadasService.saveConferencias_dictadas(conferencias_dictadas);
		return "redirect:/Conferencias_dictadas";
	}
	@GetMapping("/showFormForConferenciaDictadaUpdate/{id}")
	public String showFormForConferenciaDictadaUpdate(@PathVariable ( value = "id") long id, Model model) {
		// get employee from the service
		List<Profesor> profesor =  profesorRepository.findAll();
		model.addAttribute("profesor", profesor);
		
		Conferencias_dictadas conferencias_dictadas = Conferencias_dictadasService.getConferencias_dictadasyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("conferencias_dictadas", conferencias_dictadas);
		return "update_Conferencia_Dictada";
	}
	@GetMapping("/deleteConferenciaDictada/{id}")
	public String deletePersonaa(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.Conferencias_dictadasService.deleteConferencias_dictadasById(id);
		return "redirect:/Conferencias_dictadas";
	}
	@GetMapping("/pageConferenciasDictadas/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Conferencias_dictadas> page = Conferencias_dictadasService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Conferencias_dictadas> listConferencias_dictadas = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listConferencias_dictadas", listConferencias_dictadas);
		return "index_Conferencias_dictadas";
	}
	
}
