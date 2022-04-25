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
import net.javaguides.springboot.springsecurity.model.Asistencia_conferencias;
import net.javaguides.springboot.springsecurity.service.AlumnoService;
import net.javaguides.springboot.springsecurity.service.Asistencia_conferenciasService;

@Controller
public class Asistencia_conferenciasController {
	@Autowired
	private Asistencia_conferenciasService Asistencia_conferenciasService;
	
	@GetMapping("/Asistencia_conferencias")
	public String viewHomePage(Model model) {
		return findPaginated(1, "tipoasistencia", "asc", model);		
	}

	@GetMapping("/showNewAsistenciaConferenciasForm")
	public String showNewAsistenciaConferenciaForm(Model model) {
		// create model attribute to bind form data
		 
		Asistencia_conferencias asistencia_conferencias = new Asistencia_conferencias();
		model.addAttribute("asistencia_conferencias", asistencia_conferencias);
		return "New_AsistenciaConferencias";
	}
	

	@PostMapping("/saveConferencias")
	public String saveConferencia(@ModelAttribute("asistencia_conferencias") Asistencia_conferencias asistencia_conferencias) {
		// save employee to database

		Asistencia_conferenciasService.saveAsistencia_conferencias(asistencia_conferencias);
		return "redirect:/Asistencia_conferencias";
	}
	@GetMapping("/showFormForAsistenciaConferenciasUpdate/{id}")
	public String showFormForConferenciaUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		Asistencia_conferencias asistencia_conferencias = Asistencia_conferenciasService.getAsistencia_conferenciasyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("asistencia_conferencias", asistencia_conferencias);
		return "update_Asistencia_conferencias";
	}
	@GetMapping("/deleteConferencias/{id}")
	public String deleteConferencia(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.Asistencia_conferenciasService.deleteAsistencia_conferenciasById(id);
		return "redirect:/Asistencia_conferencias";
	}
	@GetMapping("/pageAsistenciaConferencias/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Asistencia_conferencias> page = Asistencia_conferenciasService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Asistencia_conferencias> listAsistencia_conferencias = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listAsistencia_conferencias", listAsistencia_conferencias);
		return "index_AsistenciaConferencias";
	}
	
}
