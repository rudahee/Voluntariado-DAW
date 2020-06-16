package modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(VoluntarioTareaId.class)
public class VoluntarioTarea {
	@Id
	@ManyToOne
	private TareaImpl tarea;
	@Id
	@ManyToOne
	private VoluntarioImpl voluntario;
	private String feedbackDeVoluntario;
	private String feedbackDeTarea;

	public VoluntarioTarea(TareaImpl tarea, VoluntarioImpl voluntario) {
		this.tarea = tarea;
		this.voluntario = voluntario;
	}

	public VoluntarioTarea() {

	}

	public TareaImpl getTarea() {
		return tarea;
	}

	public void setTarea(TareaImpl tarea) {
		this.tarea = tarea;
	}

	public VoluntarioImpl getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(VoluntarioImpl voluntario) {
		this.voluntario = voluntario;
	}

	public String getFeedbackDeVoluntario() {
		return feedbackDeVoluntario;
	}

	public void setFeedbackDeVoluntario(String feedbackDeVoluntario) {
		this.feedbackDeVoluntario = feedbackDeVoluntario;
	}

	public String getFeedbackDeTarea() {
		return feedbackDeTarea;
	}

	public void setFeedbackDeTarea(String feedbackDeTarea) {
		this.feedbackDeTarea = feedbackDeTarea;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tarea == null) ? 0 : tarea.hashCode());
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
		VoluntarioTarea other = (VoluntarioTarea) obj;
		if (tarea == null) {
			if (other.tarea != null)
				return false;
		} else if (!tarea.equals(other.tarea))
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
		return "VoluntarioTarea [tarea=" + tarea + ", voluntario=" + voluntario + ", feedbackDeVoluntario="
				+ feedbackDeVoluntario + ", feedbackDeTarea=" + feedbackDeTarea + "]";
	}

}
