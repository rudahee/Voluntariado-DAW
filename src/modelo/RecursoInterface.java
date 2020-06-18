package modelo;

public interface RecursoInterface {

	int getId();

	void setId(int id);

	String getNombre();

	void setNombre(String nombre);

	int getCantidad();

	void setCantidad(int cantidad);

	Tarea getTarea();

	void setTarea(Tarea tarea);

	int hashCode();

	boolean equals(Object obj);

}