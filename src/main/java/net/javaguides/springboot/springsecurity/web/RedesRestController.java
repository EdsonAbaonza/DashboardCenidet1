package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Redes;
import net.javaguides.springboot.springsecurity.service.RedesService;

@RestController
@RequestMapping("/Redes")
public class RedesRestController {
	
	@Autowired
	RedesService red;
	@GetMapping("/ObtenRed/{id}")
	public ResponseEntity<Redes> getRedes(@PathVariable("id") long id){
		
		Redes redes = red.getRedesyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(redes);
	}

}
