package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LineasInvestigacionCuerpos {
	
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
	private String cuerpoacademico;
	private String integrantes;
	private String red;
	
	 @ManyToOne
	 @JoinColumn
	 private Linea lineas;
	 
	 @ManyToOne
	 @JoinColumn
	 private Profesor profesores;
	 
	 @ManyToOne
	 @JoinColumn
	 private Redes redes;
	 
	public LineasInvestigacionCuerpos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCuerpoacademico() {
		return cuerpoacademico;
	}

	public void setCuerpoacademico(String cuerpoacademico) {
		this.cuerpoacademico = cuerpoacademico;
	}
	

	public String getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(String integrantes) {
		this.integrantes = integrantes;
	}

	public Linea getLineas() {
		return lineas;
	}

	public void setLineas(Linea lineas) {
		this.lineas = lineas;
	}

	public Profesor getProfesores() {
		return profesores;
	}

	public void setProfesores(Profesor profesores) {
		this.profesores = profesores;
	}

	public String getRed() {
		return red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public Redes getRedes() {
		return redes;
	}

	public void setRedes(Redes redes) {
		this.redes = redes;
	}

	
}
