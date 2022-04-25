package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Asistencia_conferencias;

public interface  Asistencia_conferenciasService {
	List<Asistencia_conferencias> getAllAsistencia_conferencias();
	void saveAsistencia_conferencias(Asistencia_conferencias asistencia_conferencias);
	Asistencia_conferencias getAsistencia_conferenciasyId(long id);
	void deleteAsistencia_conferenciasById(long id);
	Page<Asistencia_conferencias> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
