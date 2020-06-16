package vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import controlador.Controller;
import modelo.OrganizacionImpl;
import modelo.VoluntarioImpl;

public class Main {

	static Scanner teclado = new Scanner(System.in);
	static Controller controlador = new Controller();
	public static void main(String[] args) throws ParseException {
		int usuario;
		
		
		usuario = leerEntero("Seleccione la opcion\n1) Organizacion\n2) Voluntario\n\n>> ");
		
		if (usuario == 1) {
			menuOrganizacion();
			
		} else {
			menuVoluntario();
		}
		
		
		
	}	
	
	private static void menuVoluntario() {
		int opcion = 0;	
		int existe;
		int id;

		
			existe = leerEntero("Selecciona la opcion:\n1) Tengo una cuenta existente\n2) Registrarme");

		
		if (existe == 1) {
			do {
				id = leerEntero("ID: ");
			} while(controlador.comprobarUsuario(id));
			VoluntarioImpl usuario = controlador.obtenerVoluntario(id);
			System.out.println("Tu ID: " + usuario.getId());
			
		} else {
			String nombre = leerCadena("Nombre: ");
			String apellidos  = leerCadena("Apellidos: ");
			VoluntarioImpl usuario = controlador.crearVoluntario(nombre, apellidos);
			System.out.println("Tu ID: " + usuario.getId());
		}
		
		
		
		do {
			imprimirMenuVoluntario();
			switch (opcion) {
				case 1:
					System.out.println(controlador.verProyectos());
					break;
				case 2:
					
					break;					
				case 3:
					break;				
				case 4:
					break;	
				case 5:
					break;
				case 6:
					break;
				case 7:
					System.out.println("Adios");
					break;
			}
		} while (opcion != 7);
	}


	private static void imprimirMenuVoluntario() {
		System.out.println("1) Ver Proyectos");
		System.out.println("2) Unirse a un proyecto");
		System.out.println("3) Buscar tareas en un proyecto");
		System.out.println("4) Unirse a una tarea.");
		System.out.println("5) Dar feedback de una tarea");
		System.out.println("6) Agregar una Aptitud");
		System.out.println("7) Salir");
	}

	private static void imprimirMenuOrganizacion() {
		System.out.println("1) Ver proyectos");
		System.out.println("2) Crear proyecto");
		System.out.println("3) Ver tareas");
		System.out.println("4) Crear tarea");
		System.out.println("5) Empezar una tarea");
		System.out.println("6) Finalizar una tarea.");
		System.out.println("7) Cancelar una tarea");
		System.out.println("8) Ver los voluntarios en una tarea");
		System.out.println("9) Dar feedback de los voluntarios");
		System.out.println("10) Dar aptitud a un voluntario");
		System.out.println("11) Salir");
	}
	
	private static void menuOrganizacion() throws ParseException {
		int opcion = 0;	
		int existe, id;
		
		OrganizacionImpl organizacion;
		
		String nombre, descripcion, localizacion;
		Date fechaInicio = new Date(), fechaFinalizacion = new Date();
		SimpleDateFormat formatearFecha = new SimpleDateFormat("dd/MM/yyyy");
		
		existe = leerEntero("Selecciona la opcion:\n1) Tengo una cuenta existente\n2) Registrarme");
		
		if (existe == 1) {
			do {
				id = leerEntero("ID: ");
			} while(controlador.comprobarOrganizacion(id));
			
			organizacion = controlador.obtenerOrganizacion(id);
			System.out.println("Tu ID: " + organizacion.getId());
			
		} else {
			String nombreOrganizacion = leerCadena("Nombre: ");
			
			organizacion = controlador.crearOrganizacion(nombreOrganizacion);
			System.out.println("Tu ID: " + organizacion.getId());
		}
		
		
		
		do {
			imprimirMenuOrganizacion();
			
			opcion = leerEntero("opcion: ");
			
			switch (opcion) {
				case 1:
					System.out.println(controlador.verProyectosOrganizacion(organizacion));
					break;
				case 2:
					nombre = leerCadena("Nombre del proyecto: ");
					descripcion = leerCadena("Descripcion del proyecto: ");
					localizacion = leerCadena("Localizacion del proyecto: ");
					fechaInicio = formatearFecha.parse(leerCadena("Fecha de inicio:"));
					fechaFinalizacion = formatearFecha.parse(leerCadena("Fecha de finalizacion"));
					
					controlador.crearProyecto(nombre, descripcion, localizacion, fechaInicio, fechaFinalizacion, organizacion);
					break;					
				case 3:
					System.out.println(controlador.verTareas(idProyecto));
					break;				
				case 4:
					
					break;	
				case 5:
					break;
				case 6:
					break;
				case 7:
					System.out.println("Adiosito");
					break;
			}
		} while (opcion != 7);
	}
	

	public static String leerCadena(String msg) {
		System.out.println(msg);
		return teclado.nextLine();
	}
	
	public static int leerEntero(String msg) {
		System.out.println(msg);
		return Integer.parseInt(teclado.nextLine());
	}
	
}



