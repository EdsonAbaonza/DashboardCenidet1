package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotNull;

@Entity
public class Cursos {
	 
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Id
	    private Long id;
	 	private String nombrecurso;
	    private String fecha;
	    private String imparte;
	    private String tipocurso;
	    private String numerohoras;
	    private String enlaceevidencia;
	    private String enlaceconacyt;
	    private String enlaceprodep;
	    private String claveinstitucion;
	    private String status;
	    
	    @ManyToOne
		 @JoinColumn
		 private Profesor profesor;
	    
	    public Cursos() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombrecurso() {
			return nombrecurso;
		}

		public void setNombrecurso(String nombrecurso) {
			this.nombrecurso = nombrecurso;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		

		public String getImparte() {
			return imparte;
		}

		public void setImparte(String imparte) {
			this.imparte = imparte;
		}

		public String getTipocurso() {
			return tipocurso;
		}

		public void setTipocurso(String tipocurso) {
			this.tipocurso = tipocurso;
		}

		public String getNumerohoras() {
			return numerohoras;
		}

		public void setNumerohoras(String numerohoras) {
			this.numerohoras = numerohoras;
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

		public String getClaveinstitucion() {
			return claveinstitucion;
		}

		public void setClaveinstitucion(String claveinstitucion) {
			this.claveinstitucion = claveinstitucion;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Profesor getProfesor() {
			return profesor;
		}

		public void setProfesor(Profesor profesor) {
			this.profesor = profesor;
		}
	    
}
