package modelo;

public interface RecursoImpl {

	int getId();

	void setId(int id);

	String getNombre();

	void setNombre(String nombre);

	int getCantidad();

	void setCantidad(int cantidad);

	TareaImpl getTarea();

	void setTarea(TareaImpl tarea);

	int hashCode();

	boolean equals(Object obj);

}