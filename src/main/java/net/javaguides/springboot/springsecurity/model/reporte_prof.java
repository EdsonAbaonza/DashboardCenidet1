package net.javaguides.springboot.springsecurity.model;

public class reporte_prof {

    long profesor_id;
    String fecha_reporte;
	String nombre;
    String nombrecurso;
	String fecha;
	String tipocurso;
	String numerohoras;
	String claveinstitucion;
	int status;
	String alumno;
	String titulo;
	String COLUMN_11;
	
	public long getProfesor_id() {
		return profesor_id;
	}
	public void setProfesor_id(long profesor_id) {
		this.profesor_id = profesor_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getClaveinstitucion() {
		return claveinstitucion;
	}
	public void setClaveinstitucion(String claveinstitucion) {
		this.claveinstitucion = claveinstitucion;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAlumno() {
		return alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCOLUMN_11() {
		return COLUMN_11;
	}
	public void setCOLUMN_11(String cOLUMN_11) {
		COLUMN_11 = cOLUMN_11;
	}
	
	@Override
	public String toString() {
		return "reporte_prof [profesor_id=" + profesor_id + ", fecha_reporte=" + fecha_reporte + ", nombre=" + nombre
				+ ", nombrecurso=" + nombrecurso + ", fecha=" + fecha + ", tipocurso=" + tipocurso + ", numerohoras="
				+ numerohoras + ", claveinstitucion=" + claveinstitucion + ", status=" + status + ", alumno=" + alumno
				+ ", titulo=" + titulo + ", COLUMN_11=" + COLUMN_11 + "]";
	}
	

	public reporte_prof(long profesor_id, String fecha_reporte, String nombre, String nombrecurso, String fecha,
			String tipocurso, String numerohoras, String claveinstitucion, int status, String alumno, String titulo,
			String cOLUMN_11) {
		super();
		this.profesor_id = profesor_id;
		this.fecha_reporte = fecha_reporte;
		this.nombre = nombre;
		this.nombrecurso = nombrecurso;
		this.fecha = fecha;
		this.tipocurso = tipocurso;
		this.numerohoras = numerohoras;
		this.claveinstitucion = claveinstitucion;
		this.status = status;
		this.alumno = alumno;
		this.titulo = titulo;
		COLUMN_11 = cOLUMN_11;
	}
	public String getFecha_reporte() {
		return fecha_reporte;
	}
	public void setFecha_reporte(String fecha_reporte) {
		this.fecha_reporte = fecha_reporte;
	}

    
    
}
