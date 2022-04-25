package net.javaguides.springboot.springsecurity.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.springsecurity.model.Departamento;
import net.javaguides.springboot.springsecurity.service.DepartamentoService;

@RestController
@RequestMapping("/Departamento")
public class DepartamentoRestController {
	
	@Autowired
	DepartamentoService departamento;
	@GetMapping("/ObtenDepartamento/{id}")
	public ResponseEntity<Departamento> getDepartamento(@PathVariable("id") long id){
		
		Departamento departamentos = departamento.getDepartamentoyId(id);
		return ResponseEntity.status(HttpStatus.OK).body(departamentos);
		
	}

}
