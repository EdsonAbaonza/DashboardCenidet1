package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.LineaInvestigacionProfesores;


public interface LineasInvestigacionProfesoresService {

	List<LineaInvestigacionProfesores> getAllLineaInvestigacionProfesores();
	void saveLineaInvestigacionProfesores(LineaInvestigacionProfesores personaa);
	LineaInvestigacionProfesores getLineaInvestigacionProfesoresyId(long id);
	void deleteLineaInvestigacionProfesoresById(long id);
	Page<LineaInvestigacionProfesores> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
