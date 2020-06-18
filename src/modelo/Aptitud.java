package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Aptitud implements AptitudInterface {

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
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean isAdquirible() {
		return adquirible;
	}

	@Override
	public void setAdquirible(boolean adquirible) {
		this.adquirible = adquirible;
	}

	@Override
	public List<TareaAptitud> getListaTareas() {
		return listaTareas;
	}

	@Override
	public void setListaTareas(List<TareaAptitud> listaTareas) {
		this.listaTareas = listaTareas;
	}

	@Override
	public List<VoluntarioAptitud> getListaVoluntarios() {
		return listaVoluntarios;
	}

	@Override
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
