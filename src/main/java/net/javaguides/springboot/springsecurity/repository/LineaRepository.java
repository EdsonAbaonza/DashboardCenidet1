package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.Linea;


public interface LineaRepository extends JpaRepository<Linea, Long> {

	List<Linea> findByLinea(String linea);

}
