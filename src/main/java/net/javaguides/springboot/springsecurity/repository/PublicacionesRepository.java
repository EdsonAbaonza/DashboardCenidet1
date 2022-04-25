package net.javaguides.springboot.springsecurity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.Publicaciones;

public interface PublicacionesRepository extends JpaRepository<Publicaciones, Long> {
	
	int countByEstadopublicacion(String estadopublicacion);
	List<Publicaciones> findByAutor_id(long id);
}
