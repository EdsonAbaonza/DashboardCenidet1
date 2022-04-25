package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;

public interface  AlumnoService extends UserDetailsService{
	Alumno findByCorreo(String correo);
	Alumno save(UserRegistrationDto registration);
	List<Alumno> getAllAlumno();
	void saveAlumno(Alumno personaa);
	Alumno getAlumnoyId(long id);
	void deleteAlumnoById(long id);
	Page<Alumno> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
