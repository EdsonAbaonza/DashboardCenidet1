package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Cursos;

public interface  CursosService {
	List<Cursos> getAllCursos();
	void saveCursos(Cursos cursos);
	Cursos getCursosyId(long id);
	void deleteCursosById(long id);
	Page<Cursos> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
