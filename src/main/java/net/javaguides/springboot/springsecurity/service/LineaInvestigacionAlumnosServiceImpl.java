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
import net.javaguides.springboot.springsecurity.model.LineaInvestigacionAlumnos;
import net.javaguides.springboot.springsecurity.repository.LineaInvestigacionRepository;

@Service
public class LineaInvestigacionAlumnosServiceImpl implements LineaInvestigacionAlumnosService {

	
	@Autowired
	private LineaInvestigacionRepository LineaInvestigacion;
	
	RestTemplate template;
	String url = "http://linea_investigacion_alumnos";
	
	@Override
	public List<LineaInvestigacionAlumnos> getAllLineaInvestigacionAlumnos() {
		return  LineaInvestigacion.findAll();
	}

	@Override
	public void saveLineaInvestigacionAlumnos(LineaInvestigacionAlumnos personaa) {
		this.LineaInvestigacion.save(personaa);	
		
	}

	@Override
	public LineaInvestigacionAlumnos getLineaInvestigacionAlumnosyId(long id) {
		Optional<LineaInvestigacionAlumnos> optional = LineaInvestigacion.findById(id);
		LineaInvestigacionAlumnos lineaInvestigacionAlumnos = null;
		if (optional.isPresent()) {
			lineaInvestigacionAlumnos = optional.get();
		} else {
			throw new RuntimeException(" LineaInvestigacionAlumnos no encontrado para id :: " + id);
		}
		return lineaInvestigacionAlumnos;
	}

	@Override
	public void deleteLineaInvestigacionAlumnosById(long id) {
		this.LineaInvestigacion.deleteById(id);		
		
	}

	@Override
	public Page<LineaInvestigacionAlumnos> findPaginated(int pageNo, int pageSize, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.LineaInvestigacion.findAll(pageable);
	}

}
