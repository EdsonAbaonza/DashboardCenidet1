package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.model.Tesis;


@Repository
public interface ProfesorRepository  extends JpaRepository<Profesor, Long> {
	Profesor findByCorreo(String correo);
	List<Profesor> findByid(long id);
	List<Profesor> findByNombre(String nombre);
	List<Tesis> findAndTesisByid(long id);
	int countBySni(String sni);
	
}
