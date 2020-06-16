package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Recurso implements RecursoImpl {
	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private int cantidad;
	@ManyToOne
	private TareaImpl tarea;
	
	public Recurso(String nombre, int cantidad, TareaImpl tarea) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.tarea = tarea;
	}
	public Recurso() {

	}

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
	public int getCantidad() {
		return cantidad;
	}

	@Override
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public TareaImpl getTarea() {
		return tarea;
	}

	@Override
	public void setTarea(TareaImpl tarea) {
		this.tarea = tarea;
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
		Recurso other = (Recurso) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
