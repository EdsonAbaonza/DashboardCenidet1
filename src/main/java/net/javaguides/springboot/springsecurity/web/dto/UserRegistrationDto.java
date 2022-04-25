package net.javaguides.springboot.springsecurity.web.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import net.javaguides.springboot.springsecurity.constraint.FieldMatch;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmar_password", message = "The password fields must match"),
        @FieldMatch(first = "correo", second = "confirmar_correo", message = "The email fields must match")
})
public class UserRegistrationDto {

    @NotEmpty
    private String numero_control;

    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido_paterno;
    
    @NotEmpty
    private String apellido_materno;
    
    @NotNull
    private int edad;
    
    @NotEmpty
    private String nivel_academico;
    
    @NotEmpty
    private String tipo_alumno;
    
    @NotEmpty
    private String linea;
    
    @NotEmpty
    private String status;

    @Email
    @NotEmpty
    private String correo;

    @Email
    @NotEmpty
    private String confirmar_correo;
    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmar_password;

    @AssertTrue
    private Boolean terms;

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

	public String getNivel_academico() {
		return nivel_academico;
	}

	public void setNivel_academico(String nivel_academico) {
		this.nivel_academico = nivel_academico;
	}

	public String getTipo_alumno() {
		return tipo_alumno;
	}

	public void setTipo_alumno(String tipo_alumno) {
		this.tipo_alumno = tipo_alumno;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getConfirmar_correo() {
		return confirmar_correo;
	}

	public void setConfirmar_correo(String confirmar_correo) {
		this.confirmar_correo = confirmar_correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmar_password() {
		return confirmar_password;
	}

	public void setConfirmar_password(String confirmar_password) {
		this.confirmar_password = confirmar_password;
	}

	public Boolean getTerms() {
		return terms;
	}

	public void setTerms(Boolean terms) {
		this.terms = terms;
	}

    

}
