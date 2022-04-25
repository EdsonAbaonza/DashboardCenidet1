package net.javaguides.springboot.springsecurity.service;


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
import org.springframework.web.client.RestTemplate;

import net.javaguides.springboot.springsecurity.model.Alumno;
import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.repository.AlumnoRepository;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;


@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepo;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	public List<Alumno> getAllAlumno() {
		return  alumnoRepo.findAll();
	}

	@Override
	public void saveAlumno(Alumno personaa) {
		this.alumnoRepo.save(personaa);	
		
	}

	@Override
	public Alumno getAlumnoyId(long id) {
		Optional<Alumno> optional = alumnoRepo.findById(id);
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
		this.alumnoRepo.deleteById(id);		
	}

	@Override
	public Page<Alumno> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.alumnoRepo.findAll(pageable);
	}
	
	public Alumno findByCorreo(String correo){
        return alumnoRepo.findByCorreo(correo);
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
        return alumnoRepo.save(alumno);
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Alumno alumno = alumnoRepo.findByCorreo(correo);
        if (alumno == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(alumno.getNombre()+ ' ' + alumno.getApellido_paterno()+ ' ' + alumno.getApellido_materno(),
                alumno.getPassword(),
                mapRolesToAuthorities(alumno.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
	

}
