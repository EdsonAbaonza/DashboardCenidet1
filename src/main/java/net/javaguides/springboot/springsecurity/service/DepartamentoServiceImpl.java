package net.javaguides.springboot.springsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Departamento;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.repository.DepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoRepository depto;
	
	RestTemplate template;
	String url = "http://departamento";
	
	@Override
	public List<Departamento> getAllDepartamento() {
		return  depto.findAll();
	}

	@Override
	public void saveDepartamento(Departamento departamento) {
		this.depto.save(departamento);	
		
	}

	@Override
	public Departamento getDepartamentoyId(long id) {
		Optional<Departamento> optional = depto.findById(id);
		Departamento Departamento = null;
		if (optional.isPresent()) {
			Departamento = optional.get();
		} else {
			throw new RuntimeException(" Departamento no encontrado para id :: " + id);
		}
		return Departamento;
	}

	@Override
	public void deleteDepartamentoById(long id) {
		this.depto.deleteById(id);		
	}

	@Override
	public Page<Departamento> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.depto.findAll(pageable);
	}
}
