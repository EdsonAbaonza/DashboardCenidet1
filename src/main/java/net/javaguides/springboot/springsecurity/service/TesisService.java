package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.Tesis;


public interface TesisService {
	List<Tesis> getAllTesis();
	void saveTesis(Tesis tesis);
	Tesis getTesisyId(long id);
	void deleteTesisById(long id);
	Page<Tesis> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
