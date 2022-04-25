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

import net.javaguides.springboot.springsecurity.model.Redes;
import net.javaguides.springboot.springsecurity.repository.RedesRepository;

@Service
public class RedesServiceImpl implements RedesService {

	@Autowired
	private RedesRepository daored;
	
	RestTemplate template;
	String url = "http://redes";

	@Override
	public List<Redes> getAllRedes() {
		return  daored.findAll();

	}

	@Override
	public void saveRedes(Redes personaa) {
		this.daored.save(personaa);	
		
	}

	@Override
	public Redes getRedesyId(long id) {
		Optional<Redes> optional = daored.findById(id);
		Redes redes = null;
		if (optional.isPresent()) {
			redes = optional.get();
		} else {
			throw new RuntimeException(" redes no encontrado para id :: " + id);
		}
		return redes;
	}

	@Override
	public void deleteRedesById(long id) {
		this.daored.deleteById(id);		
		
	}

	@Override
	public Page<Redes> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.daored.findAll(pageable);
	
	
	}
	
}
