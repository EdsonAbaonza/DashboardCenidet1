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
import net.javaguides.springboot.springsecurity.model.Asistencia_conferencias;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.repository.Asistencia_conferencias_Repository;

@Service
public class Asistencia_conferenciasServiceImpl implements Asistencia_conferenciasService {

	@Autowired
	private Asistencia_conferencias_Repository asistencia_conferencias;
	
	
	@Override
	public List<Asistencia_conferencias> getAllAsistencia_conferencias() {
		return  asistencia_conferencias.findAll();
	}

	@Override
	public void saveAsistencia_conferencias(Asistencia_conferencias asistencia_conferencias) {
		this.asistencia_conferencias.save(asistencia_conferencias);	
		
	}

	@Override
	public Asistencia_conferencias getAsistencia_conferenciasyId(long id) {
		Optional<Asistencia_conferencias> optional = asistencia_conferencias.findById(id);
		Asistencia_conferencias asistencia_conferencias = null;
		if (optional.isPresent()) {
			asistencia_conferencias = optional.get();
		} else {
			throw new RuntimeException(" Asistencia a conferencia no encontrada para id :: " + id);
		}
		return asistencia_conferencias;
	}

	@Override
	public void deleteAsistencia_conferenciasById(long id) {
		this.asistencia_conferencias.deleteById(id);		
	}

	@Override
	public Page<Asistencia_conferencias> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.asistencia_conferencias.findAll(pageable);
	}
	

}
