package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.LineaInvestigacionProfesores;
import net.javaguides.springboot.springsecurity.service.LineasInvestigacionProfesoresService;

@RestController
@RequestMapping("/LineasProfesor")
public class LineasInvestigacionProfesorRestController {

	@Autowired
	LineasInvestigacionProfesoresService lineasProfesor;
	@GetMapping("/ObtenLineasProfesores/{id}")
	public ResponseEntity<LineaInvestigacionProfesores> getlineaProfesor(@PathVariable("id") long id){
		
		LineaInvestigacionProfesores lineasProfesores = lineasProfesor.getLineaInvestigacionProfesoresyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(lineasProfesores);
	}
}
