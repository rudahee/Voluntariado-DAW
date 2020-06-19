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

	// Variables para establecer la conexion con la base de datos (ORACLE 12c)
	StandardServiceRegistry sr;
	SessionFactory sf;
	Session session;

	public Controller() {
		sr = new StandardServiceRegistryBuilder().configure().build();
		sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		// En el constructor abrimos la sesion
		session = sf.openSession();
	}

	public void salir() {

		// Cerramos la sesion al salir
		session.close();
		sf.close();
	}

	/* ORGANIZACION */

	public Organizacion obtenerOrganizacion(int id) {
		// Devolvemos el objeto organizacion para acceder a sus metodos desde la vista

		Organizacion organizacion = session.get(Organizacion.class, id);

		return organizacion;
	}

	public Organizacion crearOrganizacion(String nombre) {
		// creamos una organizacion y la agregamos a la base de datos

		Organizacion organizacion = new Organizacion(nombre);
		session.getTransaction().begin();

		session.save(organizacion);
		session.getTransaction().commit();
		session.clear();

		return organizacion;
	}

	public boolean comprobarOrganizacion(int id) {
		Organizacion organizacion = session.get(Organizacion.class, id);

		// Si la organizacion existe
		if (organizacion.getId() == id) {
			// devolvemos false
			return false;
		} else {
			// en caso contrario, true
			return true;
		}
	}

	public void crearProyecto(String nombre, String descripcion, String localizacion, Date fechaInicio,
			Date fechaFinalizacion, Organizacion o) {
		// creamos un proyecto y lo agregamos a la base de datos

		session.getTransaction().begin();
		Proyecto p = new Proyecto(nombre, descripcion, localizacion, fechaInicio, fechaFinalizacion);

		// La vinculamos con una organizacion.
		o.addProyecto(p);

		session.save(p);
		session.getTransaction().commit();
		session.clear();
	}

	public void crearTarea(String nombre, String descripcion, Date fechaInicio, Date fechaFinalizacion,
			String localizacion, int maximoVoluntario, int minimoVoluntario, boolean trabajoIndividual, int proyecto) {
		// creamos una tarea y la agregamos a la base de datos

		Proyecto p = session.get(Proyecto.class, proyecto);

		session.getTransaction().begin();

		// Pertece a un proyecto
		Tarea t = new Tarea(nombre, descripcion, fechaInicio, fechaFinalizacion, localizacion, maximoVoluntario,
				minimoVoluntario, trabajoIndividual, p);

		session.save(t);
		session.getTransaction().commit();
		session.clear();
	}

	public String verProyectosOrganizacion(Organizacion organizacion) {
		StringBuilder sb = new StringBuilder();

		List<Proyecto> proyectos = organizacion.getListaProyectos();

		Iterator it = proyectos.iterator();

		// Vamos agregando a un String la salida del toString de cada uno de los
		// proyectos de una organizacion
		while (it.hasNext()) {
			sb.append(it.next().toString());
		}

		// devolvemos el string
		return sb.toString();
	}

	public String verTareas(int idProyecto) {
		StringBuilder sb = new StringBuilder();
		List<Tarea> tareas = session.get(Proyecto.class, idProyecto).getListaTareas();

		Iterator it = tareas.iterator();

		// Vamos agregando a un String la salida del toString de cada uno de las
		// tareas de un proyecto
		while (it.hasNext()) {
			sb.append(it.next().toString());
		}

		// devolvemos el String
		return sb.toString();
	}

	public void empezarTarea(int tareaId) {
		// Cambiamos el estado de la tarea a "INICIADA" representado como 1 en la base
		// de datos.

		Tarea t = session.get(Tarea.class, tareaId);

		if (t.getListaVoluntarios().size() < t.getMaximoVoluntario()
				&& t.getListaVoluntarios().size() > t.getMinimoVoluntario()) {
			// Solo si los voluntarios estan entre los limites fijados.
			t.setEstado(estadoEnum.INICIADA);
		}
	}

	public void finalizarTarea(int tareaId) {
		// Cambiamos el estado de la tarea a "CERRADA" representado como 3 en la base de
		// datos.
		Tarea t = session.get(Tarea.class, tareaId);

		t.setEstado(estadoEnum.CERRADA);
	}

	public void cancelarTarea(int tareaId) {
		// Cambiamos el estado de la tarea a "CANCELADA" representado como 2 en la base
		// de datos.
		Tarea t = session.get(Tarea.class, tareaId);

		t.setEstado(estadoEnum.CANCELADA);
	}

	public String verVoluntariosTarea(int tareaId) {
		StringBuilder sb = new StringBuilder();
		Tarea t = session.get(Tarea.class, tareaId);

		List<VoluntarioTarea> voluntarios = t.getListaVoluntarios();

		Iterator it = voluntarios.iterator();

		// Vamos agregando a un String la salida del toString de cada uno de las
		// voluntarios de una tarea
		while (it.hasNext()) {
			sb.append(it.next().toString());
		}

		// devolvemos el string
		return sb.toString();

	}

	public void darFeedbackDeVoluntarios(String feedback, int idTarea, int idVoluntario) {

		// Creamos esta clase solo para poder obtener el objeto con el session.get().
		VoluntarioTareaId vti = new VoluntarioTareaId(idVoluntario, idTarea);

		VoluntarioTarea vt = session.get(VoluntarioTarea.class, vti);

		session.getTransaction().begin();

		vt.setFeedbackDeVoluntario(feedback);

		session.save(vt);
		session.getTransaction().commit();
		session.clear();
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

	/* VOLUNTARIO */

	public boolean comprobarUsuario(int id) {
		Voluntario v = session.get(Voluntario.class, id);

		// Si el voluntario existe existe
		if (v.getId() == id) {
			return false;
		} else {
			return true;
		}
	}

	public Voluntario crearVoluntario(String nombre, String apellidos) {

		session.getTransaction().begin();

		Voluntario v = new Voluntario(nombre, apellidos);

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

		// Para ver todos los proyectos la unica forma que veo
		// para acceder desde el menu voluntario es hacer una Query.

		Query query = session
				.createQuery("SELECT id, nombre, descripcion, fechaInicio, fechaFinalizacion FROM Proyecto");
		StringBuilder sb = new StringBuilder();
		Iterator it = query.getResultList().iterator();

		sb.append("ID\tnombre\t\tdescripcion\t\tfechaInicio\t\tfechaFinalizacion\n");
		sb.append("==============================================================================================\n");

		// Iteramos sobre la query y vamos agregando al String que sera nuestra salida
		while (it.hasNext()) {
			Object[] objs = (Object[]) it.next();
			for (Object obj : objs) {
				sb.append(obj.toString() + "\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public void unirseProyecto(Voluntario v, int idProyecto) {

		// Obtenemos el proyecto
		Proyecto p = session.get(Proyecto.class, idProyecto);

		session.getTransaction().begin();

		// Para la relacion agregamos el proyecto a voluntario y el voluntario al
		// proyecto
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

		// Iterando sobre la lista de tareas, vamos agregando cada toString a un String
		// y lo devolvemos
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

	public void darFeedbackDeTarea(String feedback, int idTarea, int idVoluntario) {

		VoluntarioTareaId vti = new VoluntarioTareaId(idVoluntario, idTarea);

		VoluntarioTarea vt = session.get(VoluntarioTarea.class, vti);

		session.getTransaction().begin();

		vt.setFeedbackDeTarea(feedback);

		session.save(vt);
		session.getTransaction().commit();
		session.clear();
	}

	/* Metodos comunes */

	public Aptitud crearAptitud(String nombre, String descripcion, boolean adquirible) {

		session.getTransaction().begin();

		Aptitud a = new Aptitud(nombre, descripcion, adquirible);

		session.save(a);
		session.getTransaction().commit();
		session.clear();

		return a;
	}

}