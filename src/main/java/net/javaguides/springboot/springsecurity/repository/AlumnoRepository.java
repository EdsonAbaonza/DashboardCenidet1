package net.javaguides.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.springsecurity.model.Alumno;


@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
	Alumno findByCorreo(String correo);
	int countByStatus(String status);
}
