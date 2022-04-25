package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Asistencia_cursos;

public interface  Asistencia_cursosService {
	List<Asistencia_cursos> getAllAsistencia_cursos();
	void saveAsistencia_cursos(Asistencia_cursos asistencia_cursos);
	Asistencia_cursos getAsistencia_cursosyId(long id);
	void deleteAsistencia_cursosById(long id);
	Page<Asistencia_cursos> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
