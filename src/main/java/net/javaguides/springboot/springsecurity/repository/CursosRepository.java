package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Cursos;

public interface CursosRepository extends JpaRepository<Cursos, Long>{

	//Cursos findByProfesor(long id);
	List<Cursos> findByProfesor_id(long id);
}
