package net.javaguides.springboot.springsecurity.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.service.AlumnoService;
import net.javaguides.springboot.springsecurity.service.AlumnoServiceImpl;

@RestController
@RequestMapping("/Alumnos")
public class AlumnoRestController {
	
	@Autowired
	AlumnoService alumno;
	@GetMapping("/ObtenAlumno/{id}")
	public ResponseEntity<Alumno> getAlumno(@PathVariable("id")long id){
		
		Alumno alumnos = alumno.getAlumnoyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(alumnos);
	}

}
