package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Asistencia_cursos;
import net.javaguides.springboot.springsecurity.service.Asistencia_cursosService;

@RestController
@RequestMapping("AsistenciaCursos")
public class AsistenciaCursosRestController {
	
	@Autowired
	Asistencia_cursosService asistencia;
	@GetMapping("/ObtenAsistenciaCursos/{id}")
	public ResponseEntity<Asistencia_cursos> getAsistencias(@PathVariable("id") long id){
		
		Asistencia_cursos asistencias = asistencia.getAsistencia_cursosyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(asistencias);
	}

}
