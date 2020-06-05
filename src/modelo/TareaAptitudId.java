package modelo;

import java.io.Serializable;

public class TareaAptitudId implements Serializable{
	private int tarea;
	private int aptitud;
	
	public TareaAptitudId() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aptitud;
		result = prime * result + tarea;
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
		TareaAptitudId other = (TareaAptitudId) obj;
		if (aptitud != other.aptitud)
			return false;
		if (tarea != other.tarea)
			return false;
		return true;
	}
	
	
}
