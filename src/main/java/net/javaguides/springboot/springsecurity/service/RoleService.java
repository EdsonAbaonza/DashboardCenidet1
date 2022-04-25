package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Role;


public interface RoleService {

	List<Role> getAllRole();
	void saveRole(Role personaa);
	Role getRoleId(long id);
	void deleteRoleById(long id);
	Page<Role> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
