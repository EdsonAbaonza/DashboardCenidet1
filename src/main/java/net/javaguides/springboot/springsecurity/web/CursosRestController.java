package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Cursos;
import net.javaguides.springboot.springsecurity.service.CursosService;

@RestController
@RequestMapping("/Cursos")
public class CursosRestController {
	
	@Autowired
	CursosService cursos;
	@GetMapping("/ObtenCurso/{id}")
	public ResponseEntity<Cursos> getCursos(@PathVariable("id") long id){
		
		Cursos curso = cursos.getCursosyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(curso);
		
	}

}
