package modelo;

import java.util.List;

public interface VoluntarioImpl {

	int getId();

	void setId(int id);

	String getNombre();

	void setNombre(String nombre);

	String getApellido();

	void setApellido(String apellido);

	List<VoluntarioAptitud> getListaAptitudes();

	void setListaAptitudes(List<VoluntarioAptitud> listaAptitudes);

	List<VoluntarioTarea> getListaTareas();

	void setListaTareas(List<VoluntarioTarea> listaTareas);

	ProyectoImpl getProyecto();

	void setProyecto(ProyectoImpl proyecto);

	int hashCode();

	boolean equals(Object obj);

}