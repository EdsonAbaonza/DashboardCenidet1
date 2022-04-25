package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Conferencias_dictadas;

public interface  Conferencias_dictadasService {
	List<Conferencias_dictadas> getAllConferencias_dictadas();
	void saveConferencias_dictadas(Conferencias_dictadas citas_dictadas);
	Conferencias_dictadas getConferencias_dictadasyId(long id);
	void deleteConferencias_dictadasById(long id);
	Page<Conferencias_dictadas> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
