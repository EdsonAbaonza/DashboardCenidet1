package net.javaguides.springboot.springsecurity.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import net.javaguides.springboot.springsecurity.model.Departamento;
import net.javaguides.springboot.springsecurity.model.Linea;
import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.model.Publicaciones;
import net.javaguides.springboot.springsecurity.repository.DepartamentoRepository;
import net.javaguides.springboot.springsecurity.repository.LineaRepository;
import net.javaguides.springboot.springsecurity.repository.ProfesorRepository;
import net.javaguides.springboot.springsecurity.repository.PublicacionesRepository;
import net.javaguides.springboot.springsecurity.repository.lineasInvestigacionCuerposRepository;
import net.javaguides.springboot.springsecurity.service.PublicacionesService;

@Controller
public class PublicacionesController<LineasInvestigacionCuerpos> {
	@Autowired
	private PublicacionesService publicacionesService;
	
	@Autowired
	private ProfesorRepository profesorRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private lineasInvestigacionCuerposRepository cuerposRepository;
	
	@Autowired
	private LineaRepository lineaRepository;
	
	@Autowired
	private PublicacionesRepository publicacionesRepository;
	
	
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@GetMapping("/Publicaciones")
	//Publicaciones
	public String StringviewHomePage(
			@RequestParam(defaultValue = "0") String select_linea_investiga,
			@RequestParam(defaultValue = "0") String select_profesor,
			@RequestParam(defaultValue = "0") String select_cuerpoacademico,
			@RequestParam(defaultValue = "0") String select_departamento,
			@RequestParam(defaultValue = "0") String select_tipo, Model model) throws ParseException {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		model.addAttribute("usernames", username);
		
	List<Publicaciones> lista_publicaciones = publicacionesRepository.findAll();
	ArrayList profesores= new ArrayList();
	ArrayList lineas= new ArrayList();
	ArrayList cuerpos = new ArrayList();
	ArrayList departamentos = new ArrayList();
	ArrayList tipos = new ArrayList();
	for(Publicaciones i : lista_publicaciones) {
		profesores.add(i.getAutor().getNombre());
		lineas.add(i.getLinea().getLinea());
		cuerpos.add(i.getCuerpos().getCuerpoacademico());
		departamentos.add(i.getDepartamento().getNombredepartamento());
		tipos.add(i.getIndice());
	}

	
	Set<String> hashSet = new HashSet<String>(lineas);
	lineas.clear();
	lineas.addAll(hashSet);
	
	Set<String> hashSet2 = new HashSet<String>(profesores);
	profesores.clear();
	profesores.addAll(hashSet2);
	
	Set<String> hashSet3 = new HashSet<String>(cuerpos);
	cuerpos.clear();
	cuerpos.addAll(hashSet3);
	
	Set<String> hashSet4 = new HashSet<String>(departamentos);
	departamentos.clear();
	departamentos.addAll(hashSet4);
	
	Set<String> hashSet5 = new HashSet<String>(tipos);
	tipos.clear();
	tipos.addAll(hashSet5);
	
	model.addAttribute("Profesores", profesores);
	model.addAttribute("linea_invest", lineas);
	model.addAttribute("Cuerpos", cuerpos);
	model.addAttribute("Departamentos", departamentos);
	model.addAttribute("Tipos", tipos);
		
	
	if (select_linea_investiga.equals("0")) {}
	else {
		lista_publicaciones = lista_publicaciones.stream()      
			    .filter(line -> line.getLinea().getLinea().equals(select_linea_investiga))
			    .collect(Collectors.toList());
	}
	
	if (select_profesor.equals("0")) {}
	else {
		lista_publicaciones = lista_publicaciones.stream()      
			    .filter(line -> line.getAutor().getNombre().equals(select_profesor))
			    .collect(Collectors.toList());
	}
	
	if (select_cuerpoacademico.equals("0")) {}
	else {
		lista_publicaciones = lista_publicaciones.stream()      
			    .filter(line -> line.getCuerpos().getCuerpoacademico().equals(select_cuerpoacademico))
			    .collect(Collectors.toList());
	}
	
	if (select_departamento.equals("0")) {}
	else {
		lista_publicaciones = lista_publicaciones.stream()      
			    .filter(line -> line.getDepartamento().getNombredepartamento().equals(select_departamento))
			    .collect(Collectors.toList());
		}
	
	if (select_tipo.equals("0")){}
	else {
		lista_publicaciones = lista_publicaciones.stream()      
			    .filter(line -> line.getIndice().equals(select_tipo))
			    .collect(Collectors.toList());
	}

	model.addAttribute("listPublicaciones", lista_publicaciones);
                
	return findPaginated(1, "titulo", "asc", model); 

}

	@GetMapping("/showNewPublicacionesForm")
	public String showNewPublicacionesForm(Model model) {
		// create model attribute to bind form data
		List<Profesor> profesor =  profesorRepository.findAll();
		List<Departamento> departamento = departamentoRepository.findAll();
		List<net.javaguides.springboot.springsecurity.model.LineasInvestigacionCuerpos> cuerpo = cuerposRepository.findAll();
		List<Linea> linea = lineaRepository.findAll();
		model.addAttribute("profesor", profesor);
		model.addAttribute("departamento", departamento);
		model.addAttribute("cuerpo", cuerpo);
		model.addAttribute("linea", linea);
		
		Publicaciones publicaciones = new Publicaciones();
		model.addAttribute("Publicaciones", publicaciones);
		
		return "new_Publicacion";
	}
	

	@PostMapping("/savePublicaciones")
	public String savePublicaciones(@ModelAttribute("Publicaciones") Publicaciones publicaciones) {
		// save employee to database

		publicacionesService.savePublicaciones(publicaciones);
		return "redirect:/Publicaciones";
	}
	
	
	@GetMapping("/showFormForPublicacionesUpdate/{id}")
	public String showFormForPublicacionesUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		List<Profesor> profesor =  profesorRepository.findAll();
		List<Departamento> departamento = departamentoRepository.findAll();
		List<net.javaguides.springboot.springsecurity.model.LineasInvestigacionCuerpos> cuerpo = cuerposRepository.findAll();
		List<Linea> linea = lineaRepository.findAll();
		model.addAttribute("profesor", profesor);
		model.addAttribute("departamento", departamento);
		model.addAttribute("cuerpo", cuerpo);
		model.addAttribute("linea", linea);
		Publicaciones publicaciones = publicacionesService.getPublicacionesyId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("Publicaciones", publicaciones);
		publicacionesService.savePublicaciones(publicaciones);

		
		return "update_Publicacion";
	}
	
	
	@GetMapping("/deletePublicaciones/{id}")
	public String deletePublicaciones(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.publicacionesService.deletePublicacionesById(id);
		return "redirect:/Publicaciones";
	}
	
	
	@GetMapping("/pagePublicaciones/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Publicaciones> page = publicacionesService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Publicaciones> listPublicaciones = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		return "index_publicaciones";
	}
}
