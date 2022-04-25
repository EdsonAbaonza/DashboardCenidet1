package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Redes;


public interface RedesService {
	List<Redes> getAllRedes();
	void saveRedes(Redes personaa);
	Redes getRedesyId(long id);
	void deleteRedesById(long id);
	Page<Redes> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
