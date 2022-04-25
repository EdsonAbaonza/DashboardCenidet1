package net.javaguides.springboot.springsecurity.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Role {

    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	   private Set<Alumno> rolAlumno;
    
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	   private Set<Profesor> rolProfesor;
    
    public Role() {
    }
    
    public Role(String name) {
        this.name = name;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Alumno> getRolAlumno() {
		return rolAlumno;
	}

	public void setRolAlumno(Set<Alumno> rolAlumno) {
		this.rolAlumno = rolAlumno;
	}

	public Set<Profesor> getRolProfesor() {
		return rolProfesor;
	}

	public void setRolProfesor(Set<Profesor> rolProfesor) {
		this.rolProfesor = rolProfesor;
	}

	@Override
	public String toString() {
		return "Role{" + "id=" + id + ", name=" + name + ", rolAlumno=" + rolAlumno + ", rolProfesor=" + rolProfesor
				+ "]";
	}



	
}
