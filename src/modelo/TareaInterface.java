package modelo;

import java.util.Date;
import java.util.List;

public interface TareaInterface {

	int getId();

	void setId(int id);

	String getNombre();

	void setNombre(String nombre);

	String getDescripcion();

	void setDescripcion(String descripcion);

	Date getFechaInicio();

	void setFechaInicio(Date fechaInicio);

	Date getFechaFinalizacion();

	void setFechaFinalizacion(Date fechaFinalizacion);

	String getLocalizacion();

	void setLocalizacion(String localizacion);

	boolean isTrabajoIndividual();

	void setTrabajoIndividual(boolean trabajoIndividual);

	estadoEnum getEstado();

	void setEstado(estadoEnum estado);

	int getMaximoVoluntario();

	void setMaximoVoluntario(int maximoVoluntario);

	int getMinimoVoluntario();

	void setMinimoVoluntario(int minimoVoluntario);

	List<VoluntarioTarea> getListaVoluntarios();

	void setListaVoluntarios(List<VoluntarioTarea> listaVoluntarios);

	List<TareaAptitud> getListaAptitudes();

	void setListaAptitudes(List<TareaAptitud> listaAptitudes);

	List<Recurso> getListaRecursos();

	void setListaRecursos(List<Recurso> listaRecursos);

	Proyecto getProyecto();

	void setProyecto(Proyecto proyecto);

	int hashCode();

	boolean equals(Object obj);

	void addRecurso(Recurso recurso);

	String toString();

}