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
import net.javaguides.springboot.springsecurity.model.Integrantes;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.repository.IntegrantesRepository;

@Service
public class IntegrantesServiceImpl implements IntegrantesService {

	@Autowired
	private IntegrantesRepository integrantes;
	
	RestTemplate template;
	String url = "http://integrantes";
	
	@Override
	public List<Integrantes> getAllIntegrantes() {
		return  integrantes.findAll();
	}

	@Override
	public void saveIntegrantes(Integrantes integrantes) {
		this.integrantes.save(integrantes);	
		
	}

	@Override
	public Integrantes getIntegrantesyId(long id) {
		Optional<Integrantes> optional = integrantes.findById(id);
		Integrantes integrantes = null;
		if (optional.isPresent()) {
			integrantes = optional.get();
		} else {
			throw new RuntimeException(" Integrante no encontrado para id :: " + id);
		}
		return integrantes;
	}

	@Override
	public void deleteIntegrantesById(long id) {
		this.integrantes.deleteById(id);		
	}

	@Override
	public Page<Integrantes> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.integrantes.findAll(pageable);
	}
	

}
