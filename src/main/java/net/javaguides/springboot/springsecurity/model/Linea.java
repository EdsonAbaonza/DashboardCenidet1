package net.javaguides.springboot.springsecurity.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Linea {
	
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
	private String claveLinea;
	private String linea;
	private String jefe;
	private String claveDeprtament;
	
	 @OneToMany(mappedBy = "linea", cascade = CascadeType.ALL)
	   private Set<LineaInvestigacionAlumnos> lineainvestigacionalumnos;
	 
	 @OneToMany(mappedBy = "lineaprofesor", cascade = CascadeType.ALL)
	   private Set<LineaInvestigacionProfesores> lineaInvestigacionProfesores;
	 
	 @OneToMany(mappedBy = "lineas", cascade = CascadeType.ALL)
	   private Set<LineasInvestigacionCuerpos> lineasInvestigacionCuerpos ; 
  
	  
	public Linea() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClaveLinea() {
		return claveLinea;
	}

	public void setClaveLinea(String claveLinea) {
		this.claveLinea = claveLinea;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getJefe() {
		return jefe;
	}

	public void setJefe(String jefe) {
		this.jefe = jefe;
	}

	public String getClaveDeprtament() {
		return claveDeprtament;
	}

	public void setClaveDeprtament(String claveDeprtament) {
		this.claveDeprtament = claveDeprtament;
	}

	public Set<LineaInvestigacionAlumnos> getLineainvestigacionalumnos() {
		return lineainvestigacionalumnos;
	}

	public void setLineainvestigacionalumnos(Set<LineaInvestigacionAlumnos> lineainvestigacionalumnos) {
		this.lineainvestigacionalumnos = lineainvestigacionalumnos;
	}

	public Set<LineaInvestigacionProfesores> getLineaInvestigacionProfesores() {
		return lineaInvestigacionProfesores;
	}

	public void setLineaInvestigacionProfesores(Set<LineaInvestigacionProfesores> lineaInvestigacionProfesores) {
		this.lineaInvestigacionProfesores = lineaInvestigacionProfesores;
	}

	public Set<LineasInvestigacionCuerpos> getLineasInvestigacionCuerpos() {
		return lineasInvestigacionCuerpos;
	}

	public void setLineasInvestigacionCuerpos(Set<LineasInvestigacionCuerpos> lineasInvestigacionCuerpos) {
		this.lineasInvestigacionCuerpos = lineasInvestigacionCuerpos;
	}



	

}
