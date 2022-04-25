package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.springsecurity.model.Tesis;

@Repository
public interface TesisRepository extends JpaRepository<Tesis, Long> {

	int countByStatus(String status);
	int countByStatusAndLineaLike(String status, String linea);
	List<Tesis> findByLineaLike(String linea);
	List<Tesis> findByProfesor_id(long id);
	List<Tesis> findByDepartamento_id(long id);
	List<Tesis> findByDepartamento_idAndStatusAndLineaLike(long id, String status, String linea);
	List<Tesis> findByDepartamento_idAndLineaLike(Long depto, String linea);
	
	
	
	
}
