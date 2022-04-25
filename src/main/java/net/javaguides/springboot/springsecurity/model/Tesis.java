package net.javaguides.springboot.springsecurity.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lowagie.text.pdf.PdfPCell;

@Entity
public class Tesis {
	
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String titulo;
    private String fechaprobfin;
    private String fechainicio;
    private String fechagraduacion;
    private String director;
    private String codirector;
    private String status;
    private String enlaceevidencia;
	private String enlaceconacyt;
	private String enlaceprodep;
	private String numerocontrol;
	private String alumno;
	private String linea;
    
	@ManyToOne
	 @JoinColumn
	 private Profesor profesor;
	
	@ManyToOne
	 @JoinColumn
	 private Departamento departamento;
    
	public Tesis() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFechaprobfin() {
		return fechaprobfin;
	}

	public void setFechaprobfin(String fechaprobfin) {
		this.fechaprobfin = fechaprobfin;
	}

	public String getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}

	public String getFechagraduacion() {
		return fechagraduacion;
	}

	public void setFechagraduacion(String fechagraduacion) {
		this.fechagraduacion = fechagraduacion;
	}


	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCodirector() {
		return codirector;
	}

	public void setCodirector(String codirector) {
		this.codirector = codirector;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnlaceevidencia() {
		return enlaceevidencia;
	}

	public void setEnlaceevidencia(String enlaceevidencia) {
		this.enlaceevidencia = enlaceevidencia;
	}

	public String getEnlaceconacyt() {
		return enlaceconacyt;
	}

	public void setEnlaceconacyt(String enlaceconacyt) {
		this.enlaceconacyt = enlaceconacyt;
	}

	public String getEnlaceprodep() {
		return enlaceprodep;
	}

	public void setEnlaceprodep(String enlaceprodep) {
		this.enlaceprodep = enlaceprodep;
	}

	public String getNumerocontrol() {
		return numerocontrol;
	}

	public void setNumerocontrol(String numerocontrol) {
		this.numerocontrol = numerocontrol;
	}

	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}


	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
	
    
}
