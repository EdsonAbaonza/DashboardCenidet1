package net.javaguides.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.LineaInvestigacionProfesores;

public interface LineaInvestigacionProfesorRepository extends JpaRepository<LineaInvestigacionProfesores, Long>{
	int countByTipo(String tipo);

}
