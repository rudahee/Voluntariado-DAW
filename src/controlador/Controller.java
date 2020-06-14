package controlador;

import java.util.Date;
import java.util.Iterator;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import modelo.Organizacion;
import modelo.Proyecto;
import modelo.Tarea;
import modelo.Voluntario;

public class Controller {
	StandardServiceRegistry sr;
	SessionFactory sf;
	Session session;
	public Controller() {

		sr = new StandardServiceRegistryBuilder().configure().build();
		sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		session = sf.openSession();
		
	}
	
	public void salir() {
		session.close();
		sf.close();
	}
	
	public boolean comprobarUsuario(int id) {
		Voluntario v = session.get(Voluntario.class, id);

		if (v.getId() == id) {
			return false;
		} else {
			return true;
		}
	}

	public Voluntario crearVoluntario(String nombre, String apellidos) {
		Voluntario v = new Voluntario(nombre, apellidos);
		session.getTransaction().begin();
		
		session.save(v);
		session.getTransaction().commit();
		session.clear();
		
		return v;
	}

	public Voluntario obtenerVoluntario(int id) {
		Voluntario v = session.get(Voluntario.class, id);
		
		return v;
	}
	
	public String verProyectos() {
		Query query  =  session.createQuery("SELECT id, nombre, descripcion, fechaInicio, fechaFinalizacion FROM PROYECTO");

		StringBuilder sb = new StringBuilder();
		Iterator it = query.getResultList().iterator();
		sb.append("== ID  -  NOMBRE  -  DESCRIPCION - FECHA INICIO - FECHA FIN ==\n");
		while (it.hasNext()) {
			Proyecto proyecto = (Proyecto) it.next();	
			sb.append(proyecto.getId() + " " + proyecto.getNombre() + " " + proyecto.getDescripcion() + " " + proyecto.getFechaInicio() + " " + proyecto.getFechaFinalizacion()+"\n");
		}
				
		return sb.toString();
	}

	public void unirseProyecto(Voluntario v, int idProyecto) {
		session.getTransaction().begin();		
		
		Proyecto p = session.get(Proyecto.class, idProyecto);
		p.addVoluntario(v);

		session.save(v);
		session.save(p);
		session.getTransaction().commit();
		session.clear();
	}
	
	public String verTareas(Voluntario v) {
		Proyecto p = v.getProyecto();
		Iterator<Tarea> it = p.getListaTareas().iterator();
		StringBuilder sb = new StringBuilder();
		
			sb.append(it.next().toString());
		
		
		return sb.toString();
	}

	public Organizacion obtenerOrganizacion(int id) {
		Organizacion g = session.get(Organizacion.class, id);
		
		return g;
	}

	public Organizacion crearOrganizacion(String nombre) {
		Organizacion g = new Organizacion(nombre);
		session.getTransaction().begin();
		
		session.save(g);
		session.getTransaction().commit();
		session.clear();
		
		return g;
	}

	public boolean comprobarOrganziacion(int id) {
		Organizacion g = session.get(Organizacion.class, id);

		if (g.getId() == id) {
			return false;
		} else {
			return true;	
		}
	}

	public void crearProyecto(String nombre, String descripcion, String localizacion, 
			Date fechaInicio, Date fechaFinalizacion, Organizacion o) {
		
		session.getTransaction().begin();
		Proyecto p = new Proyecto(nombre, descripcion, localizacion, fechaInicio, fechaFinalizacion);
		
		o.addProyecto(p);
		
		session.save(p);
		session.getTransaction().commit();
		session.clear();
	}

}