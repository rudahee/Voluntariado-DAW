package modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(TareaAptitudId.class)
public class TareaAptitud {

	/*
	 * = CLASE TAREA-APTITUD =
	 */

	@Id
	@ManyToOne
	private Tarea tarea;
	@Id
	@ManyToOne
	private Aptitud aptitud;

	/*
	 * Constructores
	 */
	public TareaAptitud(Tarea tarea, Aptitud aptitud) {
		this.tarea = tarea;
		this.aptitud = aptitud;
	}

	public TareaAptitud() {

	}

	/*
	 * Getters y setters
	 */
	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public Aptitud getAptitud() {
		return aptitud;
	}

	public void setAptitud(Aptitud aptitud) {
		this.aptitud = aptitud;
	}

	/*
	 * Hashcode, equals y toString
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aptitud == null) ? 0 : aptitud.hashCode());
		result = prime * result + ((tarea == null) ? 0 : tarea.hashCode());
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
		TareaAptitud other = (TareaAptitud) obj;
		if (aptitud == null) {
			if (other.aptitud != null)
				return false;
		} else if (!aptitud.equals(other.aptitud))
			return false;
		if (tarea == null) {
			if (other.tarea != null)
				return false;
		} else if (!tarea.equals(other.tarea))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TareaAptitud [tarea=" + tarea + ", aptitud=" + aptitud + "]";
	}

}
