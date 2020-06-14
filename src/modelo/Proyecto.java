package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Proyecto {

	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private String descripcion;
	private String localizacion;
	private Date fechaInicio;
	private Date fechaFinalizacion;
	@OneToMany(mappedBy = "proyecto")
	private List<Tarea> listaTareas;
	@OneToMany(mappedBy = "proyecto")
	private List<Voluntario> voluntarios;
	@ManyToOne
	private Organizacion organizacion;

	public Proyecto(String nombre, String descripcion, String localizacion, Date fechaInicio,
			Date fechaFinalizacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.localizacion = localizacion;
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFinalizacion;
		listaTareas = new ArrayList<Tarea>();
		voluntarios = new ArrayList<Voluntario>();
	}

	public Proyecto() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	public List<Tarea> getListaTareas() {
		return listaTareas;
	}
	public void setListaTareas(List<Tarea> listaTareas) {
		this.listaTareas = listaTareas;
	}
	public List<Voluntario> getVoluntarios() {
		return voluntarios;
	}
	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proyecto other = (Proyecto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void addVoluntario(Voluntario voluntario) {
		voluntarios.add(voluntario);
		voluntario.setProyecto(this);
	}

	@Override
	public String toString() {
		return "Proyecto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", localizacion="
				+ localizacion + ", fechaInicio=" + fechaInicio + ", fechaFinalizacion=" + fechaFinalizacion
				+ ", listaTareas=" + listaTareas + ", voluntarios=" + voluntarios + ", organizacion=" + organizacion
				+ "]";
	}
	
	
}
