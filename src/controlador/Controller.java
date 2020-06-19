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

import modelo.Aptitud;
import modelo.Organizacion;
import modelo.OrganizacionInterface;
import modelo.Proyecto;
import modelo.ProyectoInterface;
import modelo.Tarea;
import modelo.TareaAptitud;
import modelo.TareaInterface;
import modelo.Voluntario;
import modelo.VoluntarioAptitud;
import modelo.VoluntarioInterface;
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

	
	public Organizacion obtenerOrganizacion(int id) {
		Organizacion o = session.get(Organizacion.class, id);
		
		return o;
	}
	
	public Organizacion crearOrganizacion(String nombre) {
		Organizacion g = new Organizacion(nombre);
		session.getTransaction().begin();
		
		session.save(g);
		session.getTransaction().commit();
		session.clear();
		
		return g;
	}

	public boolean comprobarOrganizacion(int id) {
		OrganizacionInterface g = session.get(Organizacion.class, id);

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
	
	public void crearTarea(String nombre, String descripcion, Date fechaInicio, Date fechaFinalizacion,
			String localizacion, int maximoVoluntario, int minimoVoluntario, boolean trabajoIndividual, int proyecto) {
		
		Proyecto p = session.get(Proyecto.class, proyecto);
		
		session.getTransaction().begin();
		
		TareaInterface t = new Tarea(nombre, descripcion, fechaInicio, fechaFinalizacion, localizacion, maximoVoluntario, minimoVoluntario, trabajoIndividual, p);
		
		session.save(t);
		session.getTransaction().commit();
		session.clear();
	}
	
	public String verProyectosOrganizacion(Organizacion organizacion) {
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
		
	public void empezarTarea(int tareaId) {
		Tarea t = session.get(Tarea.class, tareaId);
		
		if (t.getListaVoluntarios().size() < t.getMaximoVoluntario() && t.getListaVoluntarios().size() > t.getMinimoVoluntario()) {
			t.setEstado(estadoEnum.CREADA);
		}
		
	}
	
	public void finalizarTarea(int tareaId) {
		Tarea t = session.get(Tarea.class, tareaId);
		
		t.setEstado(estadoEnum.CERRADA);
	}
	
	public void cancelarTarea(int tareaId) {
		Tarea t = session.get(Tarea.class, tareaId);
		
		t.setEstado(estadoEnum.CANCELADA);	
	}
	
	public String verVoluntariosTarea(int tareaId) {
		StringBuilder sb = new StringBuilder();	
		Tarea t = session.get(Tarea.class, tareaId);
		
		List<VoluntarioTarea> voluntarios = t.getListaVoluntarios();
		
		Iterator it = voluntarios.iterator();
		
		while (it.hasNext()) {
			sb.append(it.next().toString());
		}
		
		return sb.toString();

		
	}
	
	public void darFeedbackDeVoluntarios(String feedback, int idTarea, int idVoluntario) {
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
		Query query  =  session.createQuery("SELECT id, nombre, descripcion, fechaInicio, fechaFinalizacion FROM Proyecto");
		StringBuilder sb = new StringBuilder();
		Iterator it = query.getResultList().iterator();
		
		sb.append("ID\tnombre\t\tdescripcion\t\tfechaInicio\t\tfechaFinalizacion\n");
		sb.append("==============================================================================================\n");
		
		while (it.hasNext()) {
			Object[] objs = (Object[]) it.next();
			for (Object obj: objs) {
				sb.append(obj.toString() + "\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void unirseProyecto(Voluntario v, int idProyecto) {
		Proyecto p = session.get(Proyecto.class, idProyecto);
		
		session.getTransaction().begin();		
		
		p.addVoluntario(v);
		v.setProyecto(p);
		
		session.save(v);
		session.save(p);
		session.getTransaction().commit();
		session.clear();
	}
	
	public String tareasEnProyecto(int idProyecto) {
		Proyecto p = session.get(Proyecto.class, idProyecto);
		StringBuilder sb = new StringBuilder();
		Iterator it = p.getListaTareas().iterator();
			
		System.out.println(it);
		
		while (it.hasNext()) {
			sb.append(it.next());
		}
		return sb.toString();
	}
	
	public boolean unirseTarea(int idTarea, Voluntario v) {
		boolean devolver = false;
		
		Tarea t = session.get(Tarea.class, idTarea);

		if (t.getEstado() != estadoEnum.CREADA) {
			devolver = false;
		} else {
			try {
				session.getTransaction().begin();		
				
				VoluntarioTarea vt = new VoluntarioTarea(t, v);
				
				session.save(vt);
				session.getTransaction().commit();
				session.clear();	
				
				devolver = true;
			} catch (Exception e) {
				e.getStackTrace();
			}
			
		}
		return devolver;
	}
	
	/****************************************************************/
	public String verTareas(Voluntario v) {
		Proyecto p = v.getProyecto();
		Iterator<Tarea> it = p.getListaTareas().iterator();
		StringBuilder sb = new StringBuilder();
		
			sb.append(it.next().toString());
		
		
		return sb.toString();
	}

	public Aptitud crearAptitud(String nombre, String descripcion, boolean adquirible) {
		
		session.getTransaction().begin();		
		
		Aptitud a = new Aptitud(nombre, descripcion, adquirible);

		session.save(a);
		session.getTransaction().commit();
		session.clear();	
		
		return a;
	}
	
	public void darAptitudVoluntario(int idA, int idV) {
		Voluntario v = session.get(Voluntario.class, idV);
		Aptitud a = session.get(Aptitud.class, idA);
		
		session.getTransaction().begin();		
		
		VoluntarioAptitud va = new VoluntarioAptitud(v, a);
		
		session.save(va);
		session.getTransaction().commit();
		session.clear();	
	}

	public void darFeedbackDeTarea(String feedback, int idTarea, int idVoluntario) {
		
		VoluntarioTareaId vti = new VoluntarioTareaId(idVoluntario, idTarea);
		
		VoluntarioTarea vt = session.get(VoluntarioTarea.class, vti);
		
		session.getTransaction().begin();		
		
		vt.setFeedbackDeTarea(feedback);	
		
		session.save(vt);
		session.getTransaction().commit();
		session.clear();
	}
}		