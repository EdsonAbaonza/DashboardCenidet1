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
import net.javaguides.springboot.springsecurity.model.Instituciones;
import net.javaguides.springboot.springsecurity.repository.InstitucionRepository;

@Service
public class InstitucionesServiceImpl implements InstitucionService{

	@Autowired
	private  InstitucionRepository instdao;
	
	RestTemplate template;
	String url = "http://instituciones";
	
	@Override
	public List<Instituciones> getAllInstituciones() {
		// TODO Auto-generated method stub
		return  instdao.findAll();
	}

	

	@Override
	public Instituciones getInstitucionesId(long id) {
		Optional<Instituciones> optional = instdao.findById(id);
		Instituciones instituciones = null;
		if (optional.isPresent()) {
			instituciones = optional.get();
		} else {
			throw new RuntimeException(" institucion no encontrada para id :: " + id);
		}
		return instituciones;
	}

	@Override
	public void deleteInstitucionesById(long id) {
		this.instdao.deleteById(id);		
		
	}

	@Override
	public Page<Instituciones> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.instdao.findAll(pageable);
	}



	@Override
	public void saveInstituciones(Instituciones personaa) {
		this.instdao.save(personaa);	
		
	}

}
