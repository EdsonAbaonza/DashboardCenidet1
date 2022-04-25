package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Departamento;

public interface  DepartamentoService {
	List<Departamento> getAllDepartamento();
	void saveDepartamento(Departamento departamento);
	Departamento getDepartamentoyId(long id);
	void deleteDepartamentoById(long id);
	Page<Departamento> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
