package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Asistencia_conferencias;
import net.javaguides.springboot.springsecurity.service.Asistencia_conferenciasService;

@RestController
@RequestMapping("/AsisctenciaConferencias")
public class AsistenciaConferenciasRestController {
	
	@Autowired
	Asistencia_conferenciasService asistencia;
	@GetMapping("/ObtenAsistencia/{id}")
	public ResponseEntity<Asistencia_conferencias> getAsistencias(@PathVariable("id") long id){
		
		Asistencia_conferencias asistencias = asistencia.getAsistencia_conferenciasyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(asistencias);
	}

}
