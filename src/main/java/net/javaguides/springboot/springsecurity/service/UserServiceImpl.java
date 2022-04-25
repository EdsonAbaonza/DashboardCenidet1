/*package net.javaguides.springboot.springsecurity.service;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;


@Service
public class UserServiceImpl implements AlumnoService {

	@Autowired
	private AlumnoRepository daoProd;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public List<Alumno> getAllAlumno() {
		return  daoProd.findAll();
	}

	@Override
	public void saveAlumno(Alumno personaa) {
		this.daoProd.save(personaa);	
		
	}

	@Override
	public Alumno getAlumnoyId(long id) {
		Optional<Alumno> optional = daoProd.findById(id);
		Alumno Alumno = null;
		if (optional.isPresent()) {
			Alumno = optional.get();
		} else {
			throw new RuntimeException(" Alumno no encontrado para id :: " + id);
		}
		return Alumno;
	}

	@Override
	public void deleteAlumnoById(long id) {
		this.daoProd.deleteById(id);		
	}

	@Override
	public Page<Alumno> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.daoProd.findAll(pageable);
	}
	
	public Alumno findByCorreo(String correo){
        return daoProd.findByCorreo(correo);
    }

    public Alumno save(UserRegistrationDto registration){
        Alumno alumno = new Alumno();
        alumno.setNumero_control(registration.getNumero_control());
        alumno.setNombre(registration.getNombre());
        alumno.setApellido_paterno(registration.getApellido_paterno());
        alumno.setApellido_materno(registration.getApellido_materno());
        alumno.setEdad(registration.getEdad());
        alumno.setNivel_academico(registration.getNivel_academico());
        alumno.setTipo_alumno(registration.getTipo_alumno());
        alumno.setLinea(registration.getLinea());
        alumno.setStatus(registration.getStatus());
        alumno.setCorreo(registration.getCorreo());
        alumno.setPassword(passwordEncoder.encode(registration.getPassword()));
        alumno.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return daoProd.save(alumno);
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Alumno alumno = daoProd.findByCorreo(correo);
        if (alumno == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(alumno.getCorreo(),
                alumno.getPassword(),
                mapRolesToAuthorities(alumno.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
	

}*/
