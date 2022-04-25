package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Linea;
import net.javaguides.springboot.springsecurity.service.LineaService;

@RestController
@RequestMapping("/Linea")
public class LineaRestController {

	@Autowired
	LineaService linea;
	@GetMapping("/ObtenLinea/{id}")
	public ResponseEntity<Linea> getLinea(@PathVariable("id") long id){
		
		Linea lineas = linea.getLineayId(id);
		return ResponseEntity.status(HttpStatus.OK).body(lineas);
		
	}
}
