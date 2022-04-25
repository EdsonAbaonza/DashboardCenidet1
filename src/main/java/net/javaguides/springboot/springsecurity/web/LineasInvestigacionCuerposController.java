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
import net.javaguides.springboot.springsecurity.model.LineasInvestigacionCuerpos;
import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.model.Redes;
import net.javaguides.springboot.springsecurity.repository.LineaRepository;
import net.javaguides.springboot.springsecurity.repository.ProfesorRepository;
import net.javaguides.springboot.springsecurity.repository.RedesRepository;
import net.javaguides.springboot.springsecurity.service.LineaInvestigacionCuerpoService;

@Controller
public class LineasInvestigacionCuerposController {
	
	@Autowired
	private LineaInvestigacionCuerpoService lineaInvestigacionCuerpoService;
	
	@Autowired
	private ProfesorRepository profRepo;
	
	@Autowired
	private LineaRepository linearepo;
	
	@Autowired
	private RedesRepository redesrepo;
	
	@GetMapping("/lineaInvestigacionCuerpos")
	public String viewHomePage(Model model) {
		return findPaginated(1, "id", "asc", model);		
	}
	
	@GetMapping("/showNewlineaInvestigacionCuerposForm")
	public String showNewlineaInvestigacionAlumnosServiceForm(Model model) {
		// create model attribute to bind form data
		

		List<Profesor> profesor =  profRepo.findAll();
		model.addAttribute("profesor", profesor);
		
		List<Linea> linea =  linearepo.findAll();
		model.addAttribute("linea", linea);
		
		List<Redes> redes =  redesrepo.findAll();
		model.addAttribute("redes", redes);
		
		LineasInvestigacionCuerpos personaa = new LineasInvestigacionCuerpos();
		model.addAttribute("personaa", personaa);
		
		return "new_lineaInvestigacionCuerpos";
	}
	
	@PostMapping("/savelineaInvestigacionCuerpos")
	public String savelineaInvestigacionCuerpos(@ModelAttribute("personaa") LineasInvestigacionCuerpos personaa) {
		// save employee to database

		lineaInvestigacionCuerpoService.saveLineasInvestigacionCuerpos(personaa);

		return "redirect:/lineaInvestigacionCuerpos";
	}
	@GetMapping("/showFormForlineaInvestigacionCuerposUpdate/{id}")
	public String showFormForlineaInvestigacionCuerposUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		LineasInvestigacionCuerpos personaa = lineaInvestigacionCuerpoService.getLineasInvestigacionCuerposyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("personaa", personaa);
		
		List<Profesor> profesor =  profRepo.findAll();
		model.addAttribute("profesor", profesor);
		
		List<Linea> linea =  linearepo.findAll();
		model.addAttribute("linea", linea);
		
		List<Redes> redes =  redesrepo.findAll();
		model.addAttribute("redes", redes);
		
		model.addAttribute("personaa", personaa);
		lineaInvestigacionCuerpoService.saveLineasInvestigacionCuerpos(personaa);;

		
		return "update_lineaInvestigacionCuerpos";
	}
	
	@GetMapping("/deletelineaInvestigacionCuerpos/{id}")
	public String deletelineaInvestigacionCuerpos(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.lineaInvestigacionCuerpoService.deleteLineasInvestigacionCuerposById(id);
		return "redirect:/lineaInvestigacionCuerpos";
	}
	
	@GetMapping("/pagelineaInvestigacionCuerpos/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<LineasInvestigacionCuerpos> page = lineaInvestigacionCuerpoService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<LineasInvestigacionCuerpos> listPersonaa = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPersonaas", listPersonaa);
		return "index_LineasInvestigacionCuerpos";
	}

}
