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
import net.javaguides.springboot.springsecurity.model.Conferencias_dictadas;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.repository.Conferencias_dictadas_Repository;

@Service
public class Conferencias_dictadasServiceImpl implements Conferencias_dictadasService {

	@Autowired
	private Conferencias_dictadas_Repository conferencias_dictadas;
	
	RestTemplate template;
	String url = "http://conferencias_dictadas";
	
	@Override
	public List<Conferencias_dictadas> getAllConferencias_dictadas() {
		return  conferencias_dictadas.findAll();
	}

	@Override
	public void saveConferencias_dictadas(Conferencias_dictadas conferencias_dictadas) {
		this.conferencias_dictadas.save(conferencias_dictadas);	
		
	}

	@Override
	public Conferencias_dictadas getConferencias_dictadasyId(long id) {
		Optional<Conferencias_dictadas> optional = conferencias_dictadas.findById(id);
		Conferencias_dictadas conferencias_dictadas = null;
		if (optional.isPresent()) {
			conferencias_dictadas = optional.get();
		} else {
			throw new RuntimeException(" Conferencia no encontrada para id :: " + id);
		}
		return conferencias_dictadas;
	}

	@Override
	public void deleteConferencias_dictadasById(long id) {
		this.conferencias_dictadas.deleteById(id);		
	}

	@Override
	public Page<Conferencias_dictadas> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.conferencias_dictadas.findAll(pageable);
	}
	

}
