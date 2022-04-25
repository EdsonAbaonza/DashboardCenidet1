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
import net.javaguides.springboot.springsecurity.model.Asistencia_cursos;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.repository.Asistencia_cursos_Repository;

@Service
public class Asistencia_cursosServiceImpl implements Asistencia_cursosService {

	@Autowired
	private Asistencia_cursos_Repository asistencia_cursos;
	
	RestTemplate template;
	String url = "http://asistencia_cursos";
	
	@Override
	public List<Asistencia_cursos> getAllAsistencia_cursos() {
		return  asistencia_cursos.findAll();
	}

	@Override
	public void saveAsistencia_cursos(Asistencia_cursos asistencia_cursos) {
		this.asistencia_cursos.save(asistencia_cursos);	
		
	}

	@Override
	public Asistencia_cursos getAsistencia_cursosyId(long id) {
		Optional<Asistencia_cursos> optional = asistencia_cursos.findById(id);
		Asistencia_cursos asistencia_cursos = null;
		if (optional.isPresent()) {
			asistencia_cursos = optional.get();
		} else {
			throw new RuntimeException(" Asistencia a curso no encontrada para id :: " + id);
		}
		return asistencia_cursos;
	}

	@Override
	public void deleteAsistencia_cursosById(long id) {
		this.asistencia_cursos.deleteById(id);		
	}

	@Override
	public Page<Asistencia_cursos> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.asistencia_cursos.findAll(pageable);
	}
	

}
