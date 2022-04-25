package net.javaguides.springboot.springsecurity.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
import net.javaguides.springboot.springsecurity.model.Profesor;
import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.repository.ProfesorRepository;
import net.javaguides.springboot.springsecurity.web.dto.ProfesorRegistrationDto;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;

@Service
public class ProfesorServcieImpl implements ProfesorService{

	@Autowired
	private ProfesorRepository daoProf;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	RestTemplate template;
	String url = "http://profesores";
	
	@Override
	public List<Profesor> getAllProfesor() {
		// TODO Auto-generated method stub
		return  daoProf.findAll();
	}
	
	@Override
	public void saveProfesor(Profesor personaa) {
		// TODO Auto-generated method stub
		this.daoProf.save(personaa);	

	}

	@Override
	public Profesor getProfesorId(long id) {
		Optional<Profesor> optional = daoProf.findById(id);
		Profesor Profesor = null;
		if (optional.isPresent()) {
			Profesor = optional.get();
		} else {
			throw new RuntimeException(" Profesor no encontrado para id :: " + id);
		}
		return Profesor;
	}

	@Override
	public void deleteProfesorById(long id) {
		this.daoProf.deleteById(id);		

	}

	@Override
	public Page<Profesor> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.daoProf.findAll(pageable);
	}

	public Profesor findByCorreo(String correo){
        return daoProf.findByCorreo(correo);
    }

    public Profesor save(ProfesorRegistrationDto registrationProf){
        Profesor profesor = new Profesor();
        profesor.setNumero_control(registrationProf.getNumero_control());
        profesor.setRfc(registrationProf.getRfc());
        profesor.setNombre(registrationProf.getNombre());
        profesor.setApellido_paterno(registrationProf.getApellido_paterno());
        profesor.setApellido_materno(registrationProf.getApellido_materno());
        profesor.setEdad(registrationProf.getEdad());
        profesor.setTipo_profesor(registrationProf.getTipo_profesor());
        profesor.setLinea(registrationProf.getLinea());
        profesor.setStatus(registrationProf.getStatus());
        profesor.setSni(registrationProf.getSni());
        profesor.setCorreo(registrationProf.getCorreo());
        profesor.setPassword(passwordEncoder.encode(registrationProf.getPassword()));
        profesor.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
        return daoProf.save(profesor);
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Profesor profesor = daoProf.findByCorreo(correo);
        if (profesor == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        
        return new org.springframework.security.core.userdetails.User(profesor.getNombre()+ ' ' + profesor.getApellido_paterno()+ ' ' +profesor.getApellido_materno(),
                profesor.getPassword(),
                mapRolesToAuthorities(profesor.getRoles()));
    }
    
    

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

	

}
