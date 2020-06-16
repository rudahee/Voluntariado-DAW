package modelo;

import java.util.List;

public interface AptitudImpl {

	int getId();

	void setId(int id);

	String getNombre();

	void setNombre(String nombre);

	String getDescripcion();

	void setDescripcion(String descripcion);

	boolean isAdquirible();

	void setAdquirible(boolean adquirible);

	List<TareaAptitud> getListaTareas();

	void setListaTareas(List<TareaAptitud> listaTareas);

	List<VoluntarioAptitud> getListaVoluntarios();

	void setListaVoluntarios(List<VoluntarioAptitud> listaVoluntarios);

	int hashCode();

	boolean equals(Object obj);

}