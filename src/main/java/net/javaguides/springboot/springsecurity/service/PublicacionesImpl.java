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

import net.javaguides.springboot.springsecurity.model.Publicaciones;
import net.javaguides.springboot.springsecurity.repository.PublicacionesRepository;

@Service
public class PublicacionesImpl implements PublicacionesService {
	@Autowired
	private PublicacionesRepository daoPubli;
	
	RestTemplate template;
	String url = "http://publicaciones";
	
	@Override
	public List<Publicaciones> getAllPublicaciones() {
		return  daoPubli.findAll();
	}

	@Override
	public void savePublicaciones(Publicaciones publicacion) {
		this.daoPubli.save(publicacion);	
		
	}

	@Override
	public Publicaciones getPublicacionesyId(long id) {
		Optional<Publicaciones> optional = daoPubli.findById(id);
		Publicaciones Publicaciones = null;
		if (optional.isPresent()) {
			Publicaciones = optional.get();
		} else {
			throw new RuntimeException(" Publicacion no encontrada para id :: " + id);
		}
		return Publicaciones;
	}

	@Override
	public void deletePublicacionesById(long id) {
		this.daoPubli.deleteById(id);		
	}

	@Override
	public Page<Publicaciones> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.daoPubli.findAll(pageable);
	}
	
}
