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

import net.javaguides.springboot.springsecurity.model.LineasInvestigacionCuerpos;
import net.javaguides.springboot.springsecurity.repository.lineasInvestigacionCuerposRepository;

@Service
public class LineasInvestigacionCuerposServiceImpl implements LineaInvestigacionCuerpoService{

	@Autowired
	private lineasInvestigacionCuerposRepository daoLineacuerpos;
	
	RestTemplate template;
	String url = "http://linea_investigacion_cuerpos";
	
	@Override
	public List<LineasInvestigacionCuerpos> getAllLineasInvestigacionCuerpos() {
		return  daoLineacuerpos.findAll();

	}

	@Override
	public void saveLineasInvestigacionCuerpos(LineasInvestigacionCuerpos personaa) {
		this.daoLineacuerpos.save(personaa);	
		
	}

	@Override
	public LineasInvestigacionCuerpos getLineasInvestigacionCuerposyId(long id) {
		Optional<LineasInvestigacionCuerpos> optional = daoLineacuerpos.findById(id);
		LineasInvestigacionCuerpos Linea = null;
		if (optional.isPresent()) {
			Linea = optional.get();
		} else {
			throw new RuntimeException(" Linea no encontrado para id :: " + id);
		}
		return Linea;
	}

	@Override
	public void deleteLineasInvestigacionCuerposById(long id) {
		this.daoLineacuerpos.deleteById(id);		
		
	}

	@Override
	public Page<LineasInvestigacionCuerpos> findPaginated(int pageNo, int pageSize, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.daoLineacuerpos.findAll(pageable);
	}

}
