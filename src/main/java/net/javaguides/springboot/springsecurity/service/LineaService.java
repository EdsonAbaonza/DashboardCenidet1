package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Linea;


public interface LineaService {
	List<Linea> getAllLinea();
	void saveLinea(Linea personaa);
	Linea getLineayId(long id);
	void deleteLineaById(long id);
	Page<Linea> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
