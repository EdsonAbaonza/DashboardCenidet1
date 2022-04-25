package net.javaguides.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Asistencia_cursos;

public interface Asistencia_cursos_Repository extends JpaRepository<Asistencia_cursos, Long>{

}
