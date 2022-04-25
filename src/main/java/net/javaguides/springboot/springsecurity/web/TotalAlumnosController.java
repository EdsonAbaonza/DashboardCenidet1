package net.javaguides.springboot.springsecurity.web;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.repository.Conferencias_dictadas_Repository;
import net.javaguides.springboot.springsecurity.repository.CursosRepository;
import net.javaguides.springboot.springsecurity.repository.LineaRepository;
import net.javaguides.springboot.springsecurity.repository.ProfesorRepository;
import net.javaguides.springboot.springsecurity.repository.PublicacionesRepository;
import net.javaguides.springboot.springsecurity.repository.TesisRepository;

@Controller
public class TotalAlumnosController {
	
	@Autowired
	AlumnoRepository alumnoRepository;

	@Autowired
	LineaRepository lineaRepository;

	@Autowired
	ProfesorRepository profesorRepository;

	@Autowired
	PublicacionesRepository publicacionesRepository;

	@Autowired
	CursosRepository cursosRepository;

	@Autowired
	Conferencias_dictadas_Repository conferenciasRepository;

	@Autowired
	TesisRepository tesisRepository;

	
	@GetMapping("/")
	public String root ( Model model) throws ParseException {
	

		model.addAttribute("num_profesor", profesorRepository.count());
		model.addAttribute("num_alumno", alumnoRepository.count());
		model.addAttribute("num_publicaciones", publicacionesRepository.count());
		model.addAttribute("num_cursos", cursosRepository.count());
		model.addAttribute("num_conferencias", conferenciasRepository.count());
		model.addAttribute("total_tesis", tesisRepository.count());
		return "index";
}
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
}
