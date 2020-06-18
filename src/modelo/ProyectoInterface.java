package modelo;

import java.util.Date;
import java.util.List;

public interface ProyectoInterface {

	int getId();

	void setId(int id);

	String getNombre();

	void setNombre(String nombre);

	String getDescripcion();

	void setDescripcion(String descripcion);

	String getLocalizacion();

	void setLocalizacion(String localizacion);

	Date getFechaInicio();

	void setFechaInicio(Date fechaInicio);

	Date getFechaFinalizacion();

	void setFechaFinalizacion(Date fechaFinalizacion);

	List<Tarea> getListaTareas();

	void setListaTareas(List<Tarea> listaTareas);

	List<Voluntario> getVoluntarios();

	void setVoluntarios(List<Voluntario> voluntarios);

	Organizacion getOrganizacion();

	void setOrganizacion(Organizacion organizacion);

	int hashCode();

	boolean equals(Object obj);

	void addVoluntario(Voluntario voluntario);

	void addTarea(Tarea tarea);

	String toString();

}