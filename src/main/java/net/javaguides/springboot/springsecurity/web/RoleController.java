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

import net.javaguides.springboot.springsecurity.model.Instituciones;
import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.service.RoleService;


@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/Role")
	public String viewHomePage(Model model) {
		return findPaginated(1, "id", "asc", model);		
	}

	@GetMapping("/showNewRoleForm")
	public String showNewRoleForm(Model model) {
		// create model attribute to bind form data
		 
		Role personaa = new Role();
		model.addAttribute("personaa", personaa);
		return "new_role";
	}
	@PostMapping("/saveRole")
	public String saveRole(@ModelAttribute("personaa") Role personaa) {
		// save employee to database

		roleService.saveRole(personaa);
		return "redirect:/Role";
	}
	@GetMapping("/showFormForRoleUpdate/{id}")
	public String showFormForRoleUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		Role personaa = roleService.getRoleId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("personaa", personaa);
		return "update_role";
	}
	@GetMapping("/deleteRole/{id}")
	public String deleteRole(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.roleService.deleteRoleById(id);
		return "redirect:/Role";
	}
	@GetMapping("/pageRole{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Role> page = roleService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Role> listPersonaa = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPersonaas", listPersonaa);
		return "index_role";
	}
}
