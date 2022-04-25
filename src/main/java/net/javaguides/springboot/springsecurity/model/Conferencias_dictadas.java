package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Conferencias_dictadas {
	
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Id
	    private Long id;
	 	private String tituloconferencia;
	    private String fecha;
	    private String sede;
	    private String nombreevento;
	    private String enlaceevidencia;
	    private String enlaceconacyt;
	    private String enlaceprodep;
	    private int status;
	    
	    @ManyToOne
		 @JoinColumn
		 private Profesor profesor;
	    
	    public Conferencias_dictadas() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTituloconferencia() {
			return tituloconferencia;
		}

		public void setTituloconferencia(String tituloconferencia) {
			this.tituloconferencia = tituloconferencia;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getSede() {
			return sede;
		}

		public void setSede(String sede) {
			this.sede = sede;
		}

		public String getNombreevento() {
			return nombreevento;
		}

		public void setNombreevento(String nombreevento) {
			this.nombreevento = nombreevento;
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

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public Profesor getProfesor() {
			return profesor;
		}

		public void setProfesor(Profesor profesor) {
			this.profesor = profesor;
		}

		
		
		
	    
}
