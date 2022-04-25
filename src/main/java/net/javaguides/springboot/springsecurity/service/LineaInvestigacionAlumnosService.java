package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.LineaInvestigacionAlumnos;


public interface LineaInvestigacionAlumnosService {

	List<LineaInvestigacionAlumnos> getAllLineaInvestigacionAlumnos();
	void saveLineaInvestigacionAlumnos(LineaInvestigacionAlumnos personaa);
	LineaInvestigacionAlumnos getLineaInvestigacionAlumnosyId(long id);
	void deleteLineaInvestigacionAlumnosById(long id);
	Page<LineaInvestigacionAlumnos> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
