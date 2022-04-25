package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Integrantes;

public interface  IntegrantesService {
	List<Integrantes> getAllIntegrantes();
	void saveIntegrantes(Integrantes integrantes);
	Integrantes getIntegrantesyId(long id);
	void deleteIntegrantesById(long id);
	Page<Integrantes> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
