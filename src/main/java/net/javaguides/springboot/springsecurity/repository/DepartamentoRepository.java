package net.javaguides.springboot.springsecurity.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import net.javaguides.springboot.springsecurity.model.Departamento;
import net.javaguides.springboot.springsecurity.model.Tesis;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

	void deleteById(String nombredepartamento);

	List<Departamento> findByid(long id);
	List<Departamento> findByNombredepartamento(String nombredepartamento);
	List<Tesis> findAndTesisByid(long id);

	
	
}
