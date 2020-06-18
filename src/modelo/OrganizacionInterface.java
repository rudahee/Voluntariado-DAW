package modelo;

import java.util.List;

public interface OrganizacionInterface {

	int getId();

	void setId(int id);

	String getNombre();

	void setNombre(String nombre);

	List<Proyecto> getListaProyectos();

	void setListaProyectos(List<Proyecto> listaProyectos);

	int hashCode();

	boolean equals(Object obj);

	String toString();

	void addProyecto(Proyecto proyecto);

}