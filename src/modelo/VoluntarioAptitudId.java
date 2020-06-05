package modelo;

import java.io.Serializable;

public class VoluntarioAptitudId implements Serializable{

	private int voluntario;
	private int aptitud;
	
	public VoluntarioAptitudId() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aptitud;
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
		VoluntarioAptitudId other = (VoluntarioAptitudId) obj;
		if (aptitud != other.aptitud)
			return false;
		if (voluntario != other.voluntario)
			return false;
		return true;
	}
	
	
}
