package net.javaguides.springboot.springsecurity.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
public class Profesor {
	
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
	private String rfc;
	private String numero_control;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private int edad;
    private String correo;
    private String password;
    private String tipo_profesor;
    private String linea;
    private String status;
    private String sni;
  

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usersprofesor_roles",
            joinColumns = @JoinColumn(
                    name = "profesor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    
    @ManyToOne
  	 @JoinColumn
  	 private Role role;
	 
    
	public Profesor() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRfc() {
		return rfc;
	}


	public void setRfc(String rfc) {
		this.rfc = rfc;
	}


	public String getNumero_control() {
		return numero_control;
	}


	public void setNumero_control(String numero_control) {
		this.numero_control = numero_control;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido_paterno() {
		return apellido_paterno;
	}


	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}


	public String getApellido_materno() {
		return apellido_materno;
	}


	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTipo_profesor() {
		return tipo_profesor;
	}


	public void setTipo_profesor(String tipo_profesor) {
		this.tipo_profesor = tipo_profesor;
	}


	public String getLinea() {
		return linea;
	}


	public void setLinea(String linea) {
		this.linea = linea;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getSni() {
		return sni;
	}


	public void setSni(String sni) {
		this.sni = sni;
	}


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Profesor [id=" + id + ", rfc=" + rfc + ", numero_control=" + numero_control + ", nombre=" + nombre
				+ ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno + ", edad=" + edad
				+ ", correo=" + correo + ", password=" + password + ", tipo_profesor=" + tipo_profesor + ", linea="
				+ linea + ", status=" + status + ", roles=" + roles + ", role=" + role
				+ ", lineaInvestigacionProfesores=" + "]";
	}
	
	
    
}
