package net.javaguides.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.LineaInvestigacionAlumnos;


public interface LineaInvestigacionRepository  extends JpaRepository<LineaInvestigacionAlumnos, Long>{
	int countByTipo(String tipo);

}
