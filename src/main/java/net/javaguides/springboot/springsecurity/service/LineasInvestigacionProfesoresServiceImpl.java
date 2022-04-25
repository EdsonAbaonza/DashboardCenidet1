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

import net.javaguides.springboot.springsecurity.model.LineaInvestigacionAlumnos;
import net.javaguides.springboot.springsecurity.model.LineaInvestigacionProfesores;
import net.javaguides.springboot.springsecurity.repository.LineaInvestigacionProfesorRepository;
import net.javaguides.springboot.springsecurity.repository.LineaInvestigacionRepository;

@Service
public class LineasInvestigacionProfesoresServiceImpl implements LineasInvestigacionProfesoresService {

	@Autowired
	private LineaInvestigacionProfesorRepository LineaInvestigacionProfesor;
	
	RestTemplate template;
	String url = "http://linea_investigacion_profesores";
	
	@Override
	public List<LineaInvestigacionProfesores> getAllLineaInvestigacionProfesores() {
		return  LineaInvestigacionProfesor.findAll();

	}

	@Override
	public void saveLineaInvestigacionProfesores(LineaInvestigacionProfesores personaa) {
		this.LineaInvestigacionProfesor.save(personaa);			
	}

	@Override
	public LineaInvestigacionProfesores getLineaInvestigacionProfesoresyId(long id) {
		Optional<LineaInvestigacionProfesores> optional = LineaInvestigacionProfesor.findById(id);
		LineaInvestigacionProfesores lineaInvestigacionProfesor = null;
		if (optional.isPresent()) {
			lineaInvestigacionProfesor = optional.get();
		} else {
			throw new RuntimeException(" lineaInvestigacionProfesor no encontrado para id :: " + id);
		}
		return lineaInvestigacionProfesor;
	}

	@Override
	public void deleteLineaInvestigacionProfesoresById(long id) {
		this.LineaInvestigacionProfesor.deleteById(id);		
		
	}

	@Override
	public Page<LineaInvestigacionProfesores> findPaginated(int pageNo, int pageSize, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.LineaInvestigacionProfesor.findAll(pageable);
	}

}
