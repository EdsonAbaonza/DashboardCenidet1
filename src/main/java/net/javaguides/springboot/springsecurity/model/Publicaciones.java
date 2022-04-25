package net.javaguides.springboot.springsecurity.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Publicaciones {
	 
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Id
	    private Long id;
	 	private String colaboradores;
	 	private String ano;
	    private String titulo;
	    private String estadopublicacion;
	    private int numissn;
	    private int numisbn;
	    private int volumen;
	    private String rangopaginas;
	    private String doi;
	    private String indice;
	    private String nombrelibro;
	    private int numcapitulo;
	    private String nombrecapitulo;
	    private String resumen;
	    private String enlaceevidencia;
	    private String enlaceconacyt;
	    private String enlaceprodep;
	    private String revista;
	    private String idioma;
	   

		 @ManyToOne
		 @JoinColumn
		 private LineasInvestigacionCuerpos cuerpos;
		 

		 @ManyToOne
		 @JoinColumn
		 private Profesor autor;
		 		 
		 @ManyToOne
		 @JoinColumn
		 public Departamento departamento;
		 
		 @ManyToOne
		 @JoinColumn
		 private Linea linea;
	    
		public Publicaciones() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getAno() {
			return ano;
		}

		public void setAno(String ano) {
			this.ano = ano;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getEstadopublicacion() {
			return estadopublicacion;
		}

		public void setEstadopublicacion(String estadopublicacion) {
			this.estadopublicacion = estadopublicacion;
		}

		public int getNumissn() {
			return numissn;
		}

		public void setNumissn(int numissn) {
			this.numissn = numissn;
		}

		public int getNumisbn() {
			return numisbn;
		}

		public void setNumisbn(int numisbn) {
			this.numisbn = numisbn;
		}

		public int getVolumen() {
			return volumen;
		}

		public void setVolumen(int volumen) {
			this.volumen = volumen;
		}

		public String getRangopaginas() {
			return rangopaginas;
		}

		public void setRangopaginas(String rangopaginas) {
			this.rangopaginas = rangopaginas;
		}

		public String getDoi() {
			return doi;
		}

		public void setDoi(String doi) {
			this.doi = doi;
		}

		public String getIndice() {
			return indice;
		}

		public void setIndice(String indice) {
			this.indice = indice;
		}

		public String getNombrelibro() {
			return nombrelibro;
		}

		public void setNombrelibro(String nombrelibro) {
			this.nombrelibro = nombrelibro;
		}

		public int getNumcapitulo() {
			return numcapitulo;
		}

		public void setNumcapitulo(int numcapitulo) {
			this.numcapitulo = numcapitulo;
		}

		public String getNombrecapitulo() {
			return nombrecapitulo;
		}

		public void setNombrecapitulo(String nombrecapitulo) {
			this.nombrecapitulo = nombrecapitulo;
		}

		public String getResumen() {
			return resumen;
		}

		public void setResumen(String resumen) {
			this.resumen = resumen;
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

		public String getRevista() {
			return revista;
		}

		public void setRevista(String revista) {
			this.revista = revista;
		}

		public String getIdioma() {
			return idioma;
		}

		public void setIdioma(String idioma) {
			this.idioma = idioma;
		}

		public LineasInvestigacionCuerpos getCuerpos() {
			return cuerpos;
		}

		public void setCuerpos(LineasInvestigacionCuerpos cuerpos) {
			this.cuerpos = cuerpos;
		}

		public Profesor getAutor() {
			return autor;
		}

		public void setAutor(Profesor autor) {
			this.autor = autor;
		}

		public String getColaboradores() {
			return colaboradores;
		}

		public void setColaboradores(String colaboradores) {
			this.colaboradores = colaboradores;
		}

		public Departamento getDepartamento() {
			return departamento;
		}

		public void setDepartamento(Departamento departamento) {
			this.departamento = departamento;
		}

		public Linea getLinea() {
			return linea;
		}

		public void setLinea(Linea linea) {
			this.linea = linea;
		}

		
		
			    
}
