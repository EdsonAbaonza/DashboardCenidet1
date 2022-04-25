package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Asistencia_cursos {
	
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Id
	    private Long id;
	 	private String clavecurso;
	    private String tipoasistencia;
	    private String rfc;
	    
	    public Asistencia_cursos() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}


		public String getClavecurso() {
			return clavecurso;
		}

		public void setClavecurso(String clavecurso) {
			this.clavecurso = clavecurso;
		}

		public String getTipoasistencia() {
			return tipoasistencia;
		}

		public void setTipoasistencia(String tipoasistencia) {
			this.tipoasistencia = tipoasistencia;
		}

		public String getRfc() {
			return rfc;
		}

		public void setRfc(String rfc) {
			this.rfc = rfc;
		}

	    
}
