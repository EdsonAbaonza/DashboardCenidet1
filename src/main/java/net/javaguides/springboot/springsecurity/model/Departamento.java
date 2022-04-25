package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Departamento {
	 
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Id
	    private Long id;
	 	private String nombredepartamento;
	    private String jefedepartamento;
	    private String status;
	    
	    public Departamento() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombredepartamento() {
			return nombredepartamento;
		}

		public void setNombredepartamento(String nombredepartamento) {
			this.nombredepartamento = nombredepartamento;
		}

		public String getJefedepartamento() {
			return jefedepartamento;
		}

		public void setJefedepartamento(String jefedepartamento) {
			this.jefedepartamento = jefedepartamento;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		@Override
		public String toString() {
			return "Departamento [id=" + id + ", nombredepartamento=" + nombredepartamento + ", jefedepartamento="
					+ jefedepartamento + ", status=" + status + "]";
		}
		
		
	    
}
