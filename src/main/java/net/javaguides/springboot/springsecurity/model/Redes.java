package net.javaguides.springboot.springsecurity.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Redes {
	
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
	private String nombre;
	private String jefe;
	private String status;
	
	 //@OneToMany(mappedBy = "redes", cascade = CascadeType.ALL)
	   //private Set<LineasInvestigacionCuerpos> lineasInvestigacionCuerpos;
	 
	public Redes() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getJefe() {
		return jefe;
	}

	public void setJefe(String jefe) {
		this.jefe = jefe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*public Set<LineasInvestigacionCuerpos> getLineasInvestigacionCuerpos() {
		return lineasInvestigacionCuerpos;
	}


	public void setLineasInvestigacionCuerpos(Set<LineasInvestigacionCuerpos> lineasInvestigacionCuerpos) {
		this.lineasInvestigacionCuerpos = lineasInvestigacionCuerpos;
	}*/
	
	
	
}
