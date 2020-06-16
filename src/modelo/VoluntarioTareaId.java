package modelo;

import java.io.Serializable;

public class VoluntarioTareaId implements Serializable{
	private int tarea;
	private int voluntario;

	public VoluntarioTareaId() {
	}

	public VoluntarioTareaId(int idVoluntario, int idTarea) {
		this.tarea = idTarea;
		this.voluntario = idVoluntario;
				
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + tarea;
		result = prime * result + voluntario;
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
		VoluntarioTareaId other = (VoluntarioTareaId) obj;
		if (tarea != other.tarea)
			return false;
		if (voluntario != other.voluntario)
			return false;
		return true;
	}
	
	
}
