package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import modelo.Organizacion;
import modelo.OrganizacionImpl;
import modelo.Proyecto;
import modelo.ProyectoImpl;
import modelo.Tarea;
import modelo.TareaImpl;
import modelo.Voluntario;
import modelo.VoluntarioImpl;
import modelo.VoluntarioTarea;
import modelo.VoluntarioTareaId;
import modelo.estadoEnum;

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
	
	
	/* ORGANIZACION */
	
	public OrganizacionImpl obtenerOrganizacion(int id) {
		OrganizacionImpl g = session.get(Organizacion.class, id);
		
		return g;
	}

	public OrganizacionImpl crearOrganizacion(String nombre) {
		OrganizacionImpl g = new Organizacion(nombre);
		session.getTransaction().begin();
		
		session.save(g);
		session.getTransaction().commit();
		session.clear();
		
		return g;
	}

	public boolean comprobarOrganizacion(int id) {
		OrganizacionImpl g = session.get(Organizacion.class, id);

		if (g.getId() == id) {
			return false;
		} else {
			return true;	
		}
	}

	public void crearProyecto(String nombre, String descripcion, String localizacion, 
			Date fechaInicio, Date fechaFinalizacion, OrganizacionImpl o) {
		
		session.getTransaction().begin();
		Proyecto p = new Proyecto(nombre, descripcion, localizacion, fechaInicio, fechaFinalizacion);
		
		o.addProyecto(p);
		
		session.save(p);
		session.getTransaction().commit();
		session.clear();
	}
	
	public void crearTarea(String nombre, String descripcion, Date fechaInicio, Date fechaFinalizacion,
			String localizacion, int maximoVoluntario, int minimoVoluntario, boolean trabajoIndividual, int proyecto) {
		
		ProyectoImpl p = session.get(Proyecto.class, proyecto);
		
		session.getTransaction().begin();
		
		TareaImpl t = new Tarea(nombre, descripcion, fechaInicio, fechaFinalizacion, localizacion, maximoVoluntario, minimoVoluntario, trabajoIndividual, p);
		
		session.save(t);
		session.getTransaction().commit();
		session.clear();
	}
	
	public String verProyectosOrganizacion(OrganizacionImpl organizacion) {
		StringBuilder sb = new StringBuilder();
		
		List<Proyecto> proyectos  = organizacion.getListaProyectos();
		
		Iterator it = proyectos.iterator();
		
		while (it.hasNext()) {
			sb.append(it.next().toString());
		}
		
		return sb.toString();
	}

	public String verTareas(int idProyecto) {
		StringBuilder sb = new StringBuilder();	
		List<Tarea> tareas  = session.get(Proyecto.class, idProyecto).getListaTareas();
		
		Iterator it = tareas.iterator();
		
		while (it.hasNext()) {
			sb.append(it.next().toString());
		}
		
		return sb.toString();
	}
	
	public void CrearTarea(String nombre, String descripcion, Date fechaInicio, Date fechaFinalizacion,
			String localizacion, int maximoVoluntario, int minimoVoluntario, 
			boolean trabajoIndividual, int proyecto) {
		 
		ProyectoImpl p = session.get(Proyecto.class, proyecto);
		
		session.getTransaction().begin();		
	
		TareaImpl t = new Tarea(nombre, descripcion, fechaInicio, fechaFinalizacion, localizacion, maximoVoluntario, minimoVoluntario, trabajoIndividual, p);

		session.save(t);
		session.getTransaction().commit();
		session.clear();
	}
		
	public void empezarTarea(int tareaId) {
		TareaImpl t = session.get(Tarea.class, tareaId);
		
		if (t.getListaVoluntarios().size() < t.getMaximoVoluntario() && t.getListaVoluntarios().size() > t.getMinimoVoluntario()) {
			t.setEstado(estadoEnum.CREADA);
		}
		
	}
	
	public void finalizarTarea(int tareaId) {
		TareaImpl t = session.get(Tarea.class, tareaId);
		
		t.setEstado(estadoEnum.CERRADA);
	}
	
	public void cancelarTarea(int tareaId) {
		TareaImpl t = session.get(Tarea.class, tareaId);
		
		t.setEstado(estadoEnum.CANCELADA);	
	}
	
	public String verVoluntariosTarea(int tareaId) {
		StringBuilder sb = new StringBuilder();	
		TareaImpl t = session.get(Tarea.class, tareaId);
		
		List<VoluntarioTarea> voluntarios = t.getListaVoluntarios();
		
		Iterator it = voluntarios.iterator();
		
		while (it.hasNext()) {
			sb.append(it.next().toString());
		}
		
		return sb.toString();

		
	}
	
	public void darFeedback(String feedback, int idTarea, int idVoluntario) {
		VoluntarioTareaId vti = new VoluntarioTareaId(idVoluntario, idTarea);
		
		VoluntarioTarea vt = session.get(VoluntarioTarea.class, vti);
		
		vt.setFeedbackDeVoluntario(feedback);
		
		session.getTransaction().begin();		

		vt.setFeedbackDeVoluntario(feedback);
		
		session.save(vt);
		session.getTransaction().commit();
		session.clear();
	}
	
	/* VOLUNTARIO */
	
	public boolean comprobarUsuario(int id) {
		VoluntarioImpl v = session.get(Voluntario.class, id);

		if (v.getId() == id) {
			return false;
		} else {
			return true;
		}
	}

	public VoluntarioImpl crearVoluntario(String nombre, String apellidos) {
		VoluntarioImpl v = new Voluntario(nombre, apellidos);
		session.getTransaction().begin();
		
		session.save(v);
		session.getTransaction().commit();
		session.clear();
		
		return v;
	}

	public VoluntarioImpl obtenerVoluntario(int id) {
		VoluntarioImpl v = session.get(Voluntario.class, id);
		
		return v;
	}
	
	public String verProyectos() {
		Query query  =  session.createQuery("SELECT id, nombre, descripcion, fechaInicio, fechaFinalizacion FROM PROYECTO");

		StringBuilder sb = new StringBuilder();
		Iterator it = query.getResultList().iterator();
		sb.append("== ID  -  NOMBRE  -  DESCRIPCION - FECHA INICIO - FECHA FIN ==\n");
		while (it.hasNext()) {
			ProyectoImpl proyecto = (ProyectoImpl) it.next();	
			sb.append(proyecto.getId() + " " + proyecto.getNombre() + " " + proyecto.getDescripcion() + " " + proyecto.getFechaInicio() + " " + proyecto.getFechaFinalizacion()+"\n");
		}
				
		return sb.toString();
	}

	public void unirseProyecto(Voluntario v, int idProyecto) {
		session.getTransaction().begin();		
		
		ProyectoImpl p = session.get(Proyecto.class, idProyecto);
		p.addVoluntario(v);

		session.save(v);
		session.save(p);
		session.getTransaction().commit();
		session.clear();
	}
	
	public String tareasEnProyecto(int idProyecto) {
		ProyectoImpl p = session.get(Proyecto.class, idProyecto);
		StringBuilder sb = new StringBuilder();
		Iterator it = p.getListaTareas().iterator();
		
		while (it.hasNext()) {
			sb.append(it.next().toString());
		}
		return sb.toString();
		
	}
	
	public void unirseTarea(int idTarea, VoluntarioImpl v) {
		session.getTransaction().begin();		
		
		TareaImpl t = session.get(Tarea.class, idTarea);
		
		VoluntarioTarea vt = new VoluntarioTarea(t, v);
		
		session.save(vt);
		session.getTransaction().commit();
		session.clear();
	}
	
	
	
	
	
	
	/****************************************************************/
	public String verTareas(VoluntarioImpl v) {
		ProyectoImpl p = v.getProyecto();
		Iterator<Tarea> it = p.getListaTareas().iterator();
		StringBuilder sb = new StringBuilder();
		
			sb.append(it.next().toString());
		
		
		return sb.toString();
	}

}