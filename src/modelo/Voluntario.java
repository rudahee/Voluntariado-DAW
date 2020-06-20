package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Voluntario implements VoluntarioInterface {
	/*
	 * = CLASE VOLUNTARIO =
	 */

	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private String apellido;
	@OneToMany(mappedBy = "voluntario")
	private List<VoluntarioAptitud> listaAptitudes;
	@OneToMany(mappedBy = "voluntario")
	private List<VoluntarioTarea> listaTareas;
	@ManyToOne
	private Proyecto proyecto;

	/*
	 * Constructores
	 */

	public Voluntario(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		listaAptitudes = new ArrayList<VoluntarioAptitud>();
		listaTareas = new ArrayList<VoluntarioTarea>();
	}

	public Voluntario() {
		listaAptitudes = new ArrayList<VoluntarioAptitud>();
		listaTareas = new ArrayList<VoluntarioTarea>();
	}

	/*
	 * Getters y setters
	 */

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
	public String getApellido() {
		return apellido;
	}

	@Override
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public List<VoluntarioAptitud> getListaAptitudes() {
		return listaAptitudes;
	}

	@Override
	public void setListaAptitudes(List<VoluntarioAptitud> listaAptitudes) {
		this.listaAptitudes = listaAptitudes;
	}

	@Override
	public List<VoluntarioTarea> getListaTareas() {
		return listaTareas;
	}

	@Override
	public void setListaTareas(List<VoluntarioTarea> listaTareas) {
		this.listaTareas = listaTareas;
	}

	@Override
	public Proyecto getProyecto() {
		return proyecto;
	}

	@Override
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	/*
	 * Hashcode, equals y toString
	 */

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
		Voluntario other = (Voluntario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\nVoluntario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + " proyecto activo="
				+ getProyecto().getId() + "\ntareas=" + listaTareas + "]";
	}

}
