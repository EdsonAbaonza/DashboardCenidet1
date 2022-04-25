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
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.service.AlumnoService;

@Controller
public class AlumnoController {
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/Alumno")
	public String viewHomePage(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		model.addAttribute("usernames", username);
		return findPaginated(1, "nombre", "asc", model);		
	}

	@GetMapping("/showNewAlumnoForm")
	public String showNewEmpresaaForm(Model model) {
		// create model attribute to bind form data
		 
		Alumno personaa = new Alumno();
		model.addAttribute("personaa", personaa);
		return "new_alumno";
	}
	

	@PostMapping("/saveAlumno")
	public String saveAlumno(@ModelAttribute("personaa") Alumno personaa) {
		// save employee to database
		
		alumnoService.saveAlumno(personaa);
		return "redirect:/Alumno";
	}
	
	@PostMapping("/updateAlumno")
	public String updateAlumno(@ModelAttribute("personaa") Alumno personaa) {
		
		
				Alumno personaa1 = alumnoService.getAlumnoyId(personaa.getId());
				personaa1.setNumero_control(personaa.getNumero_control());
				personaa1.setNombre(personaa.getNombre());
				personaa1.setApellido_paterno(personaa.getApellido_paterno());
				personaa1.setApellido_materno(personaa.getApellido_materno());
				personaa1.setEdad(personaa.getEdad());
				personaa1.setNivel_academico(personaa.getNivel_academico());
				personaa1.setTipo_alumno(personaa.getTipo_alumno());
				personaa1.setLinea(personaa.getLinea());
				personaa1.setStatus(personaa.getStatus());
				personaa1.setCorreo(personaa.getCorreo());
		

		alumnoService.saveAlumno(personaa1);
		return "redirect:/Alumno";
	}
	
	@GetMapping("/showFormForAlumnoUpdate/{id}")
	public String showFormForAlumnoUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		Alumno personaa = alumnoService.getAlumnoyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("personaa", personaa);
		return "update_Alumno";
	}
	
	@GetMapping("/deleteAlumno/{id}")
	public String deleteAlumno(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.alumnoService.deleteAlumnoById(id);
		return "redirect:/Alumno";
	}
	@GetMapping("/pageAlumno/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Alumno> page = alumnoService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Alumno> listPersonaa = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPersonaas", listPersonaa);
		return "index_alumno";
	}
	
}
