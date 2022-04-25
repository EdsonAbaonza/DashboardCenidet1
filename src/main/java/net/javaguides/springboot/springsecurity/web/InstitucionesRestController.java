package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Instituciones;
import net.javaguides.springboot.springsecurity.service.InstitucionService;

@RestController
@RequestMapping("/Instituciones")
public class InstitucionesRestController {
	
	@Autowired
	InstitucionService institucion;
	@GetMapping("/ObtenInstitucion/{id}")
	public ResponseEntity<Instituciones> getInstituciones(@PathVariable("id") long id){
		
		Instituciones instituciones = institucion.getInstitucionesId(id);
		return ResponseEntity.status(HttpStatus.OK).body(instituciones);
	}

}
