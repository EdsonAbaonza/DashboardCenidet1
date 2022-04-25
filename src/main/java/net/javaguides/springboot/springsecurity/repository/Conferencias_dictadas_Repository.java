package net.javaguides.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Conferencias_dictadas;

public interface Conferencias_dictadas_Repository extends JpaRepository<Conferencias_dictadas, Long>{

}
