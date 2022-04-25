package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LineaInvestigacionAlumnos {

	
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
	private String nivel;
	private String tipo;
	private boolean status;
	
    @ManyToOne
    @JoinColumn(name = "FK_Alumnos", nullable = false, updatable = false)
    private Alumno lineaInvestigacionAlumnos;
    
    
    @ManyToOne
	 @JoinColumn
	 private Linea linea;
	 
    
	public LineaInvestigacionAlumnos() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
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

	public Alumno getLineaInvestigacionAlumnos() {
		return lineaInvestigacionAlumnos;
	}

	public void setLineaInvestigacionAlumnos(Alumno lineaInvestigacionAlumnos) {
		this.lineaInvestigacionAlumnos = lineaInvestigacionAlumnos;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}






	
	
}
