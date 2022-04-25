package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Asistencia_conferencias {
	 
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Id
	    private Long id;
	    private String tipoasistencia;
	    private String rfc;
	    private String claveconferencia;
	    private String numerocontrol;
	    
	    public Asistencia_conferencias() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public String getClaveconferencia() {
			return claveconferencia;
		}

		public void setClaveconferencia(String claveconferencia) {
			this.claveconferencia = claveconferencia;
		}

		public String getNumerocontrol() {
			return numerocontrol;
		}

		public void setNumerocontrol(String numerocontrol) {
			this.numerocontrol = numerocontrol;
		}

	    
}
