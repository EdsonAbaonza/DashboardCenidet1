package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.web.dto.ProfesorRegistrationDto;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;


public interface ProfesorService extends UserDetailsService{
	Profesor findByCorreo(String correo);
	Profesor save(ProfesorRegistrationDto registrationProf);
	List<Profesor> getAllProfesor();
	void saveProfesor(Profesor personaa);
	Profesor getProfesorId(long id);
	void deleteProfesorById(long id);
	Page<Profesor> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
}
