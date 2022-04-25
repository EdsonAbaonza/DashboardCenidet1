package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Publicaciones;

public interface PublicacionesService {
	List<Publicaciones> getAllPublicaciones();
	void savePublicaciones(Publicaciones publicacion);
	Publicaciones getPublicacionesyId(long id);
	void deletePublicacionesById(long id);
	Page<Publicaciones> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
