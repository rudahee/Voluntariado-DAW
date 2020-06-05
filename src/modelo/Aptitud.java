package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Aptitud {
	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	private String descripcion;
	private boolean adquirible;
	@OneToMany(mappedBy = "aptitud")
	private List<TareaAptitud> listaTareas;
	@OneToMany(mappedBy = "aptitud")
	private List<VoluntarioAptitud> listaVoluntarios;


	public Aptitud(String nombre, String descripcion, boolean adquirible) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.adquirible = adquirible;
		listaTareas = new ArrayList<TareaAptitud>();
		listaVoluntarios = new ArrayList<VoluntarioAptitud>();
	}

	public Aptitud() {
		listaTareas = new ArrayList<TareaAptitud>();
		listaVoluntarios = new ArrayList<VoluntarioAptitud>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isAdquirible() {
		return adquirible;
	}

	public void setAdquirible(boolean adquirible) {
		this.adquirible = adquirible;
	}

	public List<TareaAptitud> getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(List<TareaAptitud> listaTareas) {
		this.listaTareas = listaTareas;
	}

	public List<VoluntarioAptitud> getListaVoluntarios() {
		return listaVoluntarios;
	}

	public void setListaVoluntarios(List<VoluntarioAptitud> listaVoluntarios) {
		this.listaVoluntarios = listaVoluntarios;
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
		Aptitud other = (Aptitud) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
