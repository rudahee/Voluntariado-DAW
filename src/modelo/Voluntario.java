package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Voluntario {
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<VoluntarioAptitud> getListaAptitudes() {
		return listaAptitudes;
	}

	public void setListaAptitudes(List<VoluntarioAptitud> listaAptitudes) {
		this.listaAptitudes = listaAptitudes;
	}

	public List<VoluntarioTarea> getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(List<VoluntarioTarea> listaTareas) {
		this.listaTareas = listaTareas;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
		
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
		Voluntario other = (Voluntario) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
