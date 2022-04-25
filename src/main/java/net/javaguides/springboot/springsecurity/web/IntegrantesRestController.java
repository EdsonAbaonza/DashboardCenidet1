package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Integrantes;
import net.javaguides.springboot.springsecurity.service.IntegrantesService;

@RestController
@RequestMapping("/Integrantes")
public class IntegrantesRestController {
	
	@Autowired
	IntegrantesService integrantes;
	@GetMapping("/ObtenIntegrantes/{id}")
	public ResponseEntity<Integrantes> getIntegrantes(@PathVariable("id") long id){
		
		Integrantes integrante = integrantes.getIntegrantesyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(integrante);
	}

}
