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

import net.javaguides.springboot.springsecurity.model.Linea;
import net.javaguides.springboot.springsecurity.repository.LineaRepository;

@Service
public class LineaServiceImpl implements LineaService {

	@Autowired
	private LineaRepository daoLinea;
	
	RestTemplate template;
	String url = "http://linea";
	
	@Override
	public List<Linea> getAllLinea() {
		return  daoLinea.findAll();

	}

	@Override
	public void saveLinea(Linea personaa) {
		this.daoLinea.save(personaa);	
		
	}

	@Override
	public Linea getLineayId(long id) {
		Optional<Linea> optional = daoLinea.findById(id);
		Linea Linea = null;
		if (optional.isPresent()) {
			Linea = optional.get();
		} else {
			throw new RuntimeException(" Linea no encontrado para id :: " + id);
		}
		return Linea;
	
	}

	@Override
	public void deleteLineaById(long id) {
		this.daoLinea.deleteById(id);		
		
	}

	@Override
	public Page<Linea> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.daoLinea.findAll(pageable);
	}
	

}
