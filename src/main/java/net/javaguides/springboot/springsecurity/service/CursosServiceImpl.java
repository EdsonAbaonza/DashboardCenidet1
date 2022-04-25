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
import net.javaguides.springboot.springsecurity.model.Cursos;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.repository.CursosRepository;

@Service
public class CursosServiceImpl implements CursosService {

	@Autowired
	private CursosRepository cursos;
	
	RestTemplate template;
	String url = "http://cursos";
	
	@Override
	public List<Cursos> getAllCursos() {
		return  cursos.findAll();
	}

	@Override
	public void saveCursos(Cursos cursos) {
		this.cursos.save(cursos);	
		
	}

	@Override
	public Cursos getCursosyId(long id) {
		Optional<Cursos> optional = cursos.findById(id);
		Cursos cursos = null;
		if (optional.isPresent()) {
			cursos = optional.get();
		} else {
			throw new RuntimeException(" Curso no encontrado para id :: " + id);
		}
		return cursos;
	}

	@Override
	public void deleteCursosById(long id) {
		this.cursos.deleteById(id);		
	}

	@Override
	public Page<Cursos> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.cursos.findAll(pageable);
	}
	

}
