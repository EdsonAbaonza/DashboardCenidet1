package net.javaguides.springboot.springsecurity.web;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lowagie.text.DocumentException;

import net.javaguide.springboot.springsecurity.PDFS.PDFExporter;
import net.javaguides.springboot.springsecurity.model.Cursos;
import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.model.Publicaciones;
import net.javaguides.springboot.springsecurity.model.Tesis;
import net.javaguides.springboot.springsecurity.model.Utiles;
import net.javaguides.springboot.springsecurity.model.reporte_prof;
import net.javaguides.springboot.springsecurity.repository.CursosRepository;
import net.javaguides.springboot.springsecurity.repository.ProfesorRepository;
import net.javaguides.springboot.springsecurity.repository.PublicacionesRepository;
import net.javaguides.springboot.springsecurity.repository.TesisRepository;
import net.javaguides.springboot.springsecurity.service.ProfesorService;


@Controller
public class ProfesorController {
	@Autowired
	private ProfesorService profesorService;
	
	@Autowired
	private ProfesorRepository profesorRepository;
	@Autowired
	private CursosRepository cursosRepository;
	@Autowired
	private TesisRepository tesisRepository;
	
	@Autowired
	private PublicacionesRepository publicacionesRepository;
	
	@Autowired
    ApplicationContext context;
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(ProfesorController.class);
    
	@GetMapping("/Profesor")
	public String viewHomePage(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		model.addAttribute("usernames", username);
		return findPaginated(1, "nombre", "asc", model);
		
	}
	
	@GetMapping("/showNewProfesorForm")
	public String showNewEmpresaaForm(Model model) {
		// create model attribute to bind form data
		 
		Profesor personaa = new Profesor();
		model.addAttribute("personaa", personaa);
		return "new_Profesor";
	}
	@PostMapping("/saveProfesor")
	public String saveProfesorEmpresaa(@ModelAttribute("personaa") Profesor personaa) {
		// save employee to database
		

		profesorService.saveProfesor(personaa);
		return "redirect:/Profesor";
	}
	
	@PostMapping("/updateProfesor")
	public String updateProfesor(@ModelAttribute("personaa") Profesor personaa) {
		
		Profesor personaa1 = profesorService.getProfesorId(personaa.getId());
		personaa1.setRfc(personaa.getRfc());
		personaa1.setNumero_control(personaa.getNumero_control());
		personaa1.setNombre(personaa.getNombre());
		personaa1.setApellido_paterno(personaa.getApellido_paterno());
		personaa1.setApellido_materno(personaa.getApellido_materno());
		personaa1.setEdad(personaa.getEdad());
		personaa1.setTipo_profesor(personaa.getTipo_profesor());
		personaa1.setLinea(personaa.getLinea());
		personaa1.setStatus(personaa.getStatus());
		personaa1.setSni(personaa.getSni());
		personaa1.setCorreo(personaa.getCorreo());

		profesorService.saveProfesor(personaa1);
		return "redirect:/Profesor";
	}
	
	
	@GetMapping("/showFormForProfesorUpdate/{id}")
	public String showFormForEmpresaUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		
		// get employee from the service
		Profesor personaa = profesorService.getProfesorId(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("personaa", personaa);
		return "update_profesor";
	}
	@GetMapping("/deleteProfesor/{id}")
	public String deletePersonaa(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.profesorService.deleteProfesorById(id);
		return "redirect:/Profesor";
	}
	@GetMapping("/pageProfesor/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5000;
		
		Page<Profesor> page = profesorService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Profesor> listPersonaa = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPersonaas", listPersonaa);
		return "index_profesor";
	}
	
	/*@GetMapping("/profesor/export/pdf/{id}")
    public void exportToPDF(HttpServletResponse response, @PathVariable (value = "id") Long id) throws DocumentException, IOException {
		
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=profesores_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Profesor> profesor = profesorRepository.findByid((long)id);
        List<Tesis> tesis = tesisRepository.findByProfesor_id((long)id);
        //List<Cursos> cursos = cursosRepository.findByProfesor_id((String)nombre);
        List<Publicaciones> publicaciones = publicacionesRepository.findByAutor_id((long)id);
         
        PDFExporter exporter = new PDFExporter(profesor, tesis, publicaciones);
        exporter.export(response);
       
    }*/
	

}
