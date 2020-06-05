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
public class Tarea {
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


	public Tarea(String nombre, String descripcion, Date fechaInicio, Date fechaFinalizacion,
			String localizacion, int maximoVoluntario, int minimoVoluntario, boolean trabajoIndividual, Proyecto proyecto) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFinalizacion;
		this.localizacion = localizacion;
		this.maximoVoluntario = maximoVoluntario;
		this.minimoVoluntario = minimoVoluntario;
		this.trabajoIndividual = trabajoIndividual;
		this.estado = estadoEnum.CREADA;
		this.proyecto = proyecto;
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

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public boolean isTrabajoIndividual() {
		return trabajoIndividual;
	}

	public void setTrabajoIndividual(boolean trabajoIndividual) {
		this.trabajoIndividual = trabajoIndividual;
	}

	public estadoEnum getEstado() {
		return estado;
	}

	public void setEstado(estadoEnum estado) {
		this.estado = estado;
	}

	public int getMaximoVoluntario() {
		return maximoVoluntario;
	}

	public void setMaximoVoluntario(int maximoVoluntario) {
		this.maximoVoluntario = maximoVoluntario;
	}

	public int getMinimoVoluntario() {
		return minimoVoluntario;
	}

	public void setMinimoVoluntario(int minimoVoluntario) {
		this.minimoVoluntario = minimoVoluntario;
	}

	public List<VoluntarioTarea> getListaVoluntarios() {
		return listaVoluntarios;
	}

	public void setListaVoluntarios(List<VoluntarioTarea> listaVoluntarios) {
		this.listaVoluntarios = listaVoluntarios;
	}

	public List<TareaAptitud> getListaAptitudes() {
		return listaAptitudes;
	}

	public void setListaAptitudes(List<TareaAptitud> listaAptitudes) {
		this.listaAptitudes = listaAptitudes;
	}

	public List<Recurso> getListaRecursos() {
		return listaRecursos;
	}

	public void setListaRecursos(List<Recurso> listaRecursos) {
		this.listaRecursos = listaRecursos;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
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
		Tarea other = (Tarea) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void addRecurso(Recurso recurso) {
		listaRecursos.add(recurso);
		recurso.setTarea(this);
	}
	
}
