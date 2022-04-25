package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.LineasInvestigacionCuerpos;
import net.javaguides.springboot.springsecurity.service.LineaInvestigacionCuerpoService;

@RestController
@RequestMapping("/LineasCuerpos")
public class LineasInvestigacionCuerposRestController {
	
	@Autowired
	LineaInvestigacionCuerpoService lineaCuerpo;
	@GetMapping("/ObtenLineasCuerpos/{id}")
	public ResponseEntity<LineasInvestigacionCuerpos> getLineaCuerpos(@PathVariable("id") long id){
		
		LineasInvestigacionCuerpos lineasCuerpos = lineaCuerpo.getLineasInvestigacionCuerposyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(lineasCuerpos);
	}

}
