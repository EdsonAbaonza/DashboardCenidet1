package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Instituciones;


public interface InstitucionService {
	List<Instituciones> getAllInstituciones();
	void saveInstituciones(Instituciones personaa);
	Instituciones getInstitucionesId(long id);
	void deleteInstitucionesById(long id);
	Page<Instituciones> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
