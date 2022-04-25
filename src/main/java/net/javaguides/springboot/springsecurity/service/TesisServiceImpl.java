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

import net.javaguides.springboot.springsecurity.model.Tesis;
import net.javaguides.springboot.springsecurity.repository.TesisRepository;

@Service
public class TesisServiceImpl implements TesisService {
	@Autowired
	private TesisRepository tesis;
	
	RestTemplate template;
	String url = "http://tesis";
	
	@Override
	public List<Tesis> getAllTesis() {
		return  tesis.findAll();
	}

	@Override
	public void saveTesis(Tesis tesis) {
		this.tesis.save(tesis);	
		
	}

	@Override
	public Tesis getTesisyId(long id) {
		Optional<Tesis> optional = tesis.findById(id);
		Tesis Tesis = null;
		if (optional.isPresent()) {
			Tesis = optional.get();
		} else {
			throw new RuntimeException(" Tesis no encontrada para id :: " + id);
		}
		return Tesis;
	}

	@Override
	public void deleteTesisById(long id) {
		this.tesis.deleteById(id);		
	}

	@Override
	public Page<Tesis> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.tesis.findAll(pageable);
	}
	
}
