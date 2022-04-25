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
import net.javaguides.springboot.springsecurity.model.Asistencia_cursos;
import net.javaguides.springboot.springsecurity.service.AlumnoService;
import net.javaguides.springboot.springsecurity.service.Asistencia_cursosService;

@Controller
public class Asistencia_cursosController {
	@Autowired
	private Asistencia_cursosService Asistencia_cursosService;
	
	@GetMapping("/Asistencia_cursos")
	public String viewHomePage(Model model) {
		return findPaginated(1, "clavecurso", "asc", model);		
	}

	@GetMapping("/showNewAsistenciaCursosForm")
	public String showNewAsistenciaCursosForm(Model model) {
		// create model attribute to bind form data
		 
		Asistencia_cursos asistencia_cursos = new Asistencia_cursos();
		model.addAttribute("asistencia_cursos", asistencia_cursos);
		return "new_Asistencia_cursos";
	}
	

	@PostMapping("/saveAsistenciaCursos")
	public String saveAsistenciaCursos(@ModelAttribute("asistencia_cursos") Asistencia_cursos asistencia_cursos) {
		// save employee to database

		Asistencia_cursosService.saveAsistencia_cursos(asistencia_cursos);
		return "redirect:/Asistencia_cursos";
	}
	@GetMapping("/showFormForAsistenciaCursosUpdate/{id}")
	public String showFormForAsistenciaCursosUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		Asistencia_cursos asistencia_cursos = Asistencia_cursosService.getAsistencia_cursosyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("asistencia_cursos", asistencia_cursos);
		return "update_Asistencia_cursos";
	}
	@GetMapping("/deleteAsistenciaCursos/{id}")
	public String deleteAsistenciaCurso(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.Asistencia_cursosService.deleteAsistencia_cursosById(id);
		return "redirect:/Asistencia_cursos";
	}
	@GetMapping("/pageAsistenciaCursos/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Asistencia_cursos> page = Asistencia_cursosService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Asistencia_cursos> listAsistencia_cursos = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listAsistencia_cursos", listAsistencia_cursos);
		return "index_Asistencia_cursos";
	}
	
}
