package modelo;

import java.util.List;

public interface VoluntarioInterface {

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

	Proyecto getProyecto();

	void setProyecto(Proyecto proyecto);

	int hashCode();

	boolean equals(Object obj);

}