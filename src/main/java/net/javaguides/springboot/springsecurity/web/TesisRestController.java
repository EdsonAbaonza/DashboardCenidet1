package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Tesis;
import net.javaguides.springboot.springsecurity.service.TesisService;

@RestController
@RequestMapping("/Tesis")
public class TesisRestController {
	
	@Autowired
	TesisService tesis;
	@GetMapping("/ObtenTesis/{id}")
	public ResponseEntity<Tesis> getTesis(@PathVariable("id") long id){
		
		Tesis tesis1 = tesis.getTesisyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(tesis1);
	}

}
