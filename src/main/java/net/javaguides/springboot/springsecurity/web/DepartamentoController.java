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
import net.javaguides.springboot.springsecurity.model.Departamento;
import net.javaguides.springboot.springsecurity.service.AlumnoService;
import net.javaguides.springboot.springsecurity.service.DepartamentoService;

@Controller
public class DepartamentoController {
	@Autowired
	private DepartamentoService departamentoService;
	
	@GetMapping("/Departamento")
	public String viewHomePage(Model model) {
		return findPaginated(1, "nombredepartamento", "asc", model);		
	}

	@GetMapping("/showNewDepartamentoForm")
	public String showNewDepartamentoForm(Model model) {
		// create model attribute to bind form data
		 
		Departamento departamento = new Departamento();
		model.addAttribute("departamento", departamento);
		return "new_departamento";
	}
	

	@PostMapping("/saveDepartamento")
	public String saveDepartamento(@ModelAttribute("departamento") Departamento departamento) {
		// save employee to database

		departamentoService.saveDepartamento(departamento);
		return "redirect:/Departamento";
	}
	@GetMapping("/showFormForDepartamentoUpdate/{id}")
	public String showFormForDepartamentoUpdate(@PathVariable ( value = "id") Long id, Model model) {
		
		
		// get employee from the service
		Departamento departamento = departamentoService.getDepartamentoyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("departamento", departamento);
		return "update_Departamento";
	}
	@GetMapping("/deleteDepartamento/{id}")
	public String deleteDepartamento(@PathVariable (value = "id") Long id) {
		
		// call delete employee method 
		this.departamentoService.deleteDepartamentoById(id);
		return "redirect:/Departamento";
	}
	@GetMapping("/pageDepartamento/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Departamento> page = departamentoService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Departamento> listDepartamento = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listDepartamento", listDepartamento);
		return "index_Departamento";
	}
	
}
