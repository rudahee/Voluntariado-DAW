package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tarea implements TareaInterface {

	/*
	 * = CLASE PROYECTO =
	 */

	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFinalizacion;
	private String localizacion;
	private boolean trabajoIndividual;
	@Enumerated
	private estadoEnum estado;
	private int maximoVoluntario;
	private int minimoVoluntario;

	@OneToMany(mappedBy = "tarea")
	private List<VoluntarioTarea> listaVoluntarios;
	@OneToMany(mappedBy = "tarea")
	private List<TareaAptitud> listaAptitudes;
	@OneToMany(mappedBy = "tarea")
	private List<Recurso> listaRecursos;
	@ManyToOne
	private Proyecto proyecto;

	/*
	 * Constructores
	 */

	public Tarea(String nombre, String descripcion, Date fechaInicio, Date fechaFinalizacion, String localizacion,
			int maximoVoluntario, int minimoVoluntario, boolean trabajoIndividual, Proyecto proyecto) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFinalizacion;
		this.localizacion = localizacion;
		this.maximoVoluntario = maximoVoluntario;
		this.minimoVoluntario = minimoVoluntario;
		this.trabajoIndividual = trabajoIndividual;
		this.estado = estadoEnum.CREADA;
		setProyecto(proyecto);
		listaVoluntarios = new ArrayList<VoluntarioTarea>();
		listaAptitudes = new ArrayList<TareaAptitud>();
		listaRecursos = new ArrayList<Recurso>();
	}

	public Tarea() {
		this.estado = estadoEnum.CREADA;
		listaVoluntarios = new ArrayList<VoluntarioTarea>();
		listaAptitudes = new ArrayList<TareaAptitud>();
		listaRecursos = new ArrayList<Recurso>();
	}

	/*
	 * Getters y Setters
	 */

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
	public String getLocalizacion() {
		return localizacion;
	}

	@Override
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	@Override
	public boolean isTrabajoIndividual() {
		return trabajoIndividual;
	}

	@Override
	public void setTrabajoIndividual(boolean trabajoIndividual) {
		this.trabajoIndividual = trabajoIndividual;
	}

	@Override
	public estadoEnum getEstado() {
		return estado;
	}

	@Override
	public void setEstado(estadoEnum estado) {
		this.estado = estado;
	}

	@Override
	public int getMaximoVoluntario() {
		return maximoVoluntario;
	}

	@Override
	public void setMaximoVoluntario(int maximoVoluntario) {
		this.maximoVoluntario = maximoVoluntario;
	}

	@Override
	public int getMinimoVoluntario() {
		return minimoVoluntario;
	}

	@Override
	public void setMinimoVoluntario(int minimoVoluntario) {
		this.minimoVoluntario = minimoVoluntario;
	}

	@Override
	public List<VoluntarioTarea> getListaVoluntarios() {
		return listaVoluntarios;
	}

	@Override
	public void setListaVoluntarios(List<VoluntarioTarea> listaVoluntarios) {
		this.listaVoluntarios = listaVoluntarios;
	}

	@Override
	public List<TareaAptitud> getListaAptitudes() {
		return listaAptitudes;
	}

	@Override
	public void setListaAptitudes(List<TareaAptitud> listaAptitudes) {
		this.listaAptitudes = listaAptitudes;
	}

	@Override
	public List<Recurso> getListaRecursos() {
		return listaRecursos;
	}

	@Override
	public void setListaRecursos(List<Recurso> listaRecursos) {
		this.listaRecursos = listaRecursos;
	}

	@Override
	public Proyecto getProyecto() {
		return proyecto;
	}

	@Override
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
		proyecto.addTarea(this);
	}

	/*
	 * hashCode, equals y toString
	 */

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
		Tarea other = (Tarea) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nTarea [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha inicio="
				+ fechaInicio + ", fecha finalizacion=" + fechaFinalizacion + ", estado=" + estado
				+ ", maximos voluntarios=" + maximoVoluntario + ", minimo voluntarios=" + minimoVoluntario;
	}

	/*
	 * Otros metodos.
	 */

	@Override
	public void addRecurso(Recurso recurso) {
		listaRecursos.add(recurso);
		recurso.setTarea(this);
	}

}
