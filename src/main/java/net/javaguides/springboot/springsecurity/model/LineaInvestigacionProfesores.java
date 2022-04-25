package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LineaInvestigacionProfesores {

	
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
	private String tipo;
	private boolean status;
	
    @ManyToOne
	 @JoinColumn
	 private Profesor profesor;
    
    @ManyToOne
	 @JoinColumn
	 private Linea lineaprofesor;
	
	public LineaInvestigacionProfesores() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Linea getLineaprofesor() {
		return lineaprofesor;
	}

	public void setLineaprofesor(Linea lineaprofesor) {
		this.lineaprofesor = lineaprofesor;
	}
	
	
}
