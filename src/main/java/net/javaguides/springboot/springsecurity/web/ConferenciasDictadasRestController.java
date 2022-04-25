package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Conferencias_dictadas;
import net.javaguides.springboot.springsecurity.service.Conferencias_dictadasService;

@RestController
@RequestMapping("/ConferenciasDictadas")
public class ConferenciasDictadasRestController {
	
	@Autowired
	Conferencias_dictadasService conferencias;
	@GetMapping("/ObtenConferenciasDictadas/{id}")
	public ResponseEntity<Conferencias_dictadas> getConferencias(@PathVariable("id") long id){
		
		Conferencias_dictadas conferencia = conferencias.getConferencias_dictadasyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(conferencia);
	}

}
