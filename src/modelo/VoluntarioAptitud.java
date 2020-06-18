package modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(VoluntarioAptitudId.class)
public class VoluntarioAptitud {
	@Id
	@ManyToOne
	private Voluntario voluntario;
	@Id
	@ManyToOne
	private Aptitud aptitud;

	public VoluntarioAptitud(Voluntario voluntario, Aptitud aptitud) {
		this.voluntario = voluntario;
		this.aptitud = aptitud;
	}

	public VoluntarioAptitud() {

	}

	public Voluntario getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(Voluntario voluntario) {
		this.voluntario = voluntario;
	}

	public Aptitud getAptitud() {
		return aptitud;
	}

	public void setAptitud(Aptitud aptitud) {
		this.aptitud = aptitud;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aptitud == null) ? 0 : aptitud.hashCode());
		result = prime * result + ((voluntario == null) ? 0 : voluntario.hashCode());
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
		VoluntarioAptitud other = (VoluntarioAptitud) obj;
		if (aptitud == null) {
			if (other.aptitud != null)
				return false;
		} else if (!aptitud.equals(other.aptitud))
			return false;
		if (voluntario == null) {
			if (other.voluntario != null)
				return false;
		} else if (!voluntario.equals(other.voluntario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VoluntarioAptitud [voluntario=" + voluntario + ", aptitud=" + aptitud + "]";
	}

}
