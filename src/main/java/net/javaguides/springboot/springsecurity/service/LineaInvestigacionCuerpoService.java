package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.LineasInvestigacionCuerpos;


public interface LineaInvestigacionCuerpoService {
	List<LineasInvestigacionCuerpos> getAllLineasInvestigacionCuerpos();
	void saveLineasInvestigacionCuerpos(LineasInvestigacionCuerpos personaa);
	LineasInvestigacionCuerpos getLineasInvestigacionCuerposyId(long id);
	void deleteLineasInvestigacionCuerposById(long id);
	Page<LineasInvestigacionCuerpos> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
