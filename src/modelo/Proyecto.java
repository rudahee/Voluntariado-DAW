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
public class Proyecto implements ProyectoImpl {

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
	private OrganizacionImpl organizacion;

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
	
	@Override
	public int getId() {
		return id;
	}
	@Override
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String getNombre() {
		return nombre;
	}
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String getDescripcion() {
		return descripcion;
	}
	@Override
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String getLocalizacion() {
		return localizacion;
	}
	@Override
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
	@Override
	public Date getFechaInicio() {
		return fechaInicio;
	}
	@Override
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	@Override
	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	@Override
	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	@Override
	public List<Tarea> getListaTareas() {
		return listaTareas;
	}
	@Override
	public void setListaTareas(List<Tarea> listaTareas) {
		this.listaTareas = listaTareas;
	}
	@Override
	public List<Voluntario> getVoluntarios() {
		return voluntarios;
	}
	@Override
	public void setVoluntarios(List<Voluntario> voluntarios) {
		this.voluntarios = voluntarios;
	}
	@Override
	public OrganizacionImpl getOrganizacion() {
		return organizacion;
	}
	@Override
	public void setOrganizacion(OrganizacionImpl organizacion) {
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

	@Override
	public void addVoluntario(Voluntario voluntario) {
		voluntarios.add(voluntario);
		voluntario.setProyecto(this);
	}
	
	@Override
	public void addTarea(Tarea tarea) {
		listaTareas.add(tarea);
	}

	@Override
	public String toString() {
		return "Proyecto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", localizacion="
				+ localizacion + ", fechaInicio=" + fechaInicio + ", fechaFinalizacion=" + fechaFinalizacion
				+ ", listaTareas=" + listaTareas + ", voluntarios=" + voluntarios + ", organizacion=" + organizacion
				+ "]";
	}
	
	
}
