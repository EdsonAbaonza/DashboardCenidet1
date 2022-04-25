package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Integrantes {
	 
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Id
	    private Long id;
	 	private String clavecuerpo;
	    private String rfc;
	   
	    
	    public Integrantes() {
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getClavecuerpo() {
			return clavecuerpo;
		}


		public void setClavecuerpo(String clavecuerpo) {
			this.clavecuerpo = clavecuerpo;
		}


		public String getRfc() {
			return rfc;
		}


		public void setRfc(String rfc) {
			this.rfc = rfc;
		}

		
		
}
