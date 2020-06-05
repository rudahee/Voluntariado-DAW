package controlador;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import modelo.Aptitud;
import modelo.Organizacion;
import modelo.Proyecto;
import modelo.Voluntario;
import modelo.VoluntarioAptitud;

public class ControladorVoluntario {
	
	private static StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
	private static SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
	private static Session session = sf.openSession();
	
	public ControladorVoluntario() {
		session.getTransaction().begin();
		Voluntario voluntario = new Voluntario("Jose Ruben", "Daza Hernandez");
		Organizacion ong = new Organizacion("Intermon Oxfand");
		Proyecto ayudaEnAfrica = new Proyecto("Africa te necesita", "Ayuda en el corazon de la africa mas pobre", "Nueva Guinea", ong); 
		Aptitud apt = new Aptitud("Organizacion", "Capacidad para organizar eventos", true);
		VoluntarioAptitud volapt = new VoluntarioAptitud(voluntario, apt);
		ayudaEnAfrica.addVoluntario(voluntario);
		
		session.save(ong);
		session.save(ayudaEnAfrica);
		session.save(voluntario);
		session.save(apt);
		session.save(volapt);
		session.getTransaction().commit();
		session.close();
		sf.close();
	}
	
}
