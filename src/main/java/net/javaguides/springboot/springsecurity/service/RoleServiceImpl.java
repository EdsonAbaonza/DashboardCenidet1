package net.javaguides.springboot.springsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private  RoleRepository roledao;
	
	@Override
	public List<Role> getAllRole() {
		return  roledao.findAll();

	}

	@Override
	public void saveRole(Role personaa) {
		this.roledao.save(personaa);	
		
	}

	@Override
	public Role getRoleId(long id) {
		Optional<Role> optional = roledao.findById(id);
		Role roles = null;
		if (optional.isPresent()) {
			roles = optional.get();
		} else {
			throw new RuntimeException(" roles no encontrado para id :: " + id);
		}
		return roles;
	}

	@Override
	public void deleteRoleById(long id) {
		this.roledao.deleteById(id);		
		
	}

	@Override
	public Page<Role> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.roledao.findAll(pageable);
	}

}
