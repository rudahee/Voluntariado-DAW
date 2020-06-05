package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Organizacion {

	@Id
	@GeneratedValue
	private int id;
	private String nombre;
	@OneToMany(mappedBy = "organizacion")
	private List<Proyecto> listaProyectos;

	public Organizacion(String nombre) {
		this.nombre = nombre;
		listaProyectos = new ArrayList<Proyecto>();
	}
	
	public Organizacion() {
		listaProyectos = new ArrayList<Proyecto>();
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

	public List<Proyecto> getListaProyectos() {
		return listaProyectos;
	}

	public void setListaProyectos(List<Proyecto> listaProyectos) {
		this.listaProyectos = listaProyectos;
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
		Organizacion other = (Organizacion) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Organizacion [id=" + id + ", nombre=" + nombre + ", listaProyectos=" + listaProyectos + "]";
	}

	public void addProyecto(Proyecto proyecto) {
		listaProyectos.add(proyecto);
		proyecto.setOrganizacion(this);
	}
	
}
