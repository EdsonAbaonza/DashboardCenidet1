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
import net.javaguides.springboot.springsecurity.model.Redes;
import net.javaguides.springboot.springsecurity.service.RedesService;


@Controller
public class RedesController {
	
	@Autowired
	private RedesService redesService;
	
	@GetMapping("/Redes")
	public String viewHomePage(Model model) {
		return findPaginated(1, "nombre", "asc", model);		
	}
	
	@GetMapping("/showNewRedesForm")
	public String showNewRedesForm(Model model) {
		// create model attribute to bind form data
		 
		Redes redes = new Redes();
		model.addAttribute("redes", redes);
		return "new_redes";
	}

	@PostMapping("/saveRedes")
	public String saveRedes(@ModelAttribute("redes") Redes redes) {
		// save employee to database

		redesService.saveRedes(redes);
		return "redirect:/Redes";
	}
	
	@GetMapping("/showFormForRedesUpdate/{id}")
	public String showFormForredesServiceUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		Redes redes = redesService.getRedesyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("redes", redes);
		return "update_Redes";
	}
	
	@GetMapping("/deleteRedes/{id}")
	public String deletePersonaa(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.redesService.deleteRedesById(id);
		return "redirect:/Redes";
	}
	
	@GetMapping("/pageRedes/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Redes> page = redesService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Redes> listRedes = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listRedes", listRedes);
		return "index_Redes";
	}
}
