package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.service.ProfesorService;

@RestController
@RequestMapping("/Profesor")
public class ProfesorRestController {
	
	@Autowired
	ProfesorService profesor;
	@GetMapping("/ObtenProfesor/{id}")
	public ResponseEntity<Profesor> getProfesor (@PathVariable("id") long id){
		
		Profesor profesores = profesor.getProfesorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(profesores);
	}

}
