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
import net.javaguides.springboot.springsecurity.model.Cursos;
import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.repository.ProfesorRepository;
import net.javaguides.springboot.springsecurity.service.AlumnoService;
import net.javaguides.springboot.springsecurity.service.CursosService;

@Controller
public class CursosController {
	@Autowired
	private CursosService CursosService;
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@GetMapping("/Cursos")
	public String viewHomePage(Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		model.addAttribute("usernames", username);
		
		return findPaginated(1, "nombrecurso", "asc", model);		
	}

	@GetMapping("/showNewCursoForm")
	public String showNewCursoForm(Model model) {
		// create model attribute to bind form data
		 
		List<Profesor> profesor =  profesorRepository.findAll();
		model.addAttribute("profesor", profesor);
		
		Cursos cursos = new Cursos();
		model.addAttribute("cursos", cursos);
		return "new_cursos";
	}
	

	@PostMapping("/saveCursos")
	public String saveCursos(@ModelAttribute("cursos") Cursos cursos) {
		
		//String fk_profesores =cursos.getProfesor().getNombre();
		//String fk_profesoresCorreo=cursos.getProfesor().getCorreo();
		
		CursosService.saveCursos(cursos);
		long id = cursos.getId();

		CursosService.saveCursos(cursos);
		return "redirect:/Cursos";
	}
	@GetMapping("/showFormForCursosUpdate/{id}")
	public String showFormForCursosUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		List<Profesor> profesor =  profesorRepository.findAll();
		model.addAttribute("profesor", profesor);
		Cursos cursos = CursosService.getCursosyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("cursos", cursos);
		
		CursosService.saveCursos(cursos);
		
		return "update_Cursos";
	}
	@GetMapping("/deleteCursos/{id}")
	public String deletePersonaa(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.CursosService.deleteCursosById(id);
		return "redirect:/Cursos";
	}
	@GetMapping("/pageCursos/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Cursos> page = CursosService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Cursos> listCursos = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listCursos", listCursos);
		return "index_cursos";
	}
	
}
