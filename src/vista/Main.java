package vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import controlador.Controller;
import modelo.Aptitud;
import modelo.Organizacion;
import modelo.OrganizacionInterface;
import modelo.Voluntario;
import modelo.VoluntarioInterface;
import oracle.net.aso.v;

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
	/* VOLUNTARIO */
	private static void menuVoluntario() {
		int opcion = 0;	
		int existe, id;
		int idVoluntario = 0;
		Voluntario v;
		String feedback;
		
		existe = leerEntero("Selecciona la opcion:\n1) Tengo una cuenta existente\n2) Registrarme");

		if (existe == 1) {
			do {
				idVoluntario = leerEntero("ID: ");
			} while(controlador.comprobarUsuario(idVoluntario));
			v = controlador.obtenerVoluntario(idVoluntario);
			System.out.println("Tu ID: " + v.getId());
			
		} else {
			String nombre = leerCadena("Nombre: ");
			String apellidos  = leerCadena("Apellidos: ");
			v = controlador.crearVoluntario(nombre, apellidos);
			System.out.println("Tu ID: " + v.getId());
		}
		
		
		
		do {
			imprimirMenuVoluntario();
			
			opcion = leerEntero("opcion: ");
			
			switch (opcion) {
				case 1:
					System.out.println(controlador.verProyectos());
					break;
				case 2:
					id = leerEntero("Introduce el id del proyecto: ");
					controlador.unirseProyecto(v, id);
					break;					
				case 3:
					id = leerEntero("ID del proyecto: ");
					System.out.println(controlador.tareasEnProyecto(id));
					break;				
				case 4:
					id = leerEntero("ID de la tarea: ");
					if (!controlador.unirseTarea(id, v)) {
						System.out.println("No se ha podido unir a la tarea.");
					}
					break;	
				case 5:
					feedback = leerCadena("feedback del voluntario: ");
					controlador.darFeedbackDeTarea(feedback, leerEntero("ID de la tarea: "), leerEntero("ID del voluntario: "));
					break;
				case 6:
					darAptitud(v.getId());
					break;
				case 7:
					System.out.println(v.toString());
					break;
				case 8:
					System.out.println("Adios");
					break;
			}
		} while (opcion != 8);
	}


	private static void imprimirMenuVoluntario() {
		System.out.println("1) Ver Proyectos");
		System.out.println("2) Unirse a un proyecto");
		System.out.println("3) Buscar tareas en un proyecto");
		System.out.println("4) Unirse a una tarea.");
		System.out.println("5) Dar feedback de una tarea ##");
		System.out.println("6) Agregar una Aptitud");
		System.out.println("7) Ver mi informacion");
		System.out.println("8) Salir");
	}

	/*  
	 *		Metodos de organizacion
	 */
	private static void menuOrganizacion() throws ParseException {
		int opcion = 0;	
		int existe, id, idOrganizacion, minimo, maximo;
		boolean individual;
		
		Organizacion organizacion;
		
		String nombre, descripcion, localizacion, feedback;
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
					id = leerEntero("ID del proyecto: ");
					controlador.verTareas(id);
					break;				
				case 4:
					nombre = leerCadena("Introduce el nombre de la tarea: ");
					descripcion = leerCadena("Introduce la descripcion: ");
					localizacion = leerCadena("Introduce la localizacion: ");
					fechaInicio = formatearFecha.parse(leerCadena("Introduce la fecha de inicio: "));
					fechaFinalizacion = formatearFecha.parse(leerCadena("Introduce la fehca de finalizacion: "));
					minimo = leerEntero("Introduce el minimo de voluntarios: ");
					maximo = leerEntero("Introduce el maximo de voluntarios: ");
					individual = leerBoolean("Trabajo individual? ");
					id = leerEntero("Proyecto en el que crear la tarea: ");
					
					controlador.crearTarea(nombre, descripcion, fechaInicio, fechaFinalizacion, 
							localizacion, maximo, minimo, individual, id);
					break;	
				case 5:
					id = leerEntero("Id de la tarea a empezar: ");
					controlador.empezarTarea(id);
					break;
				case 6:
					id = leerEntero("Id de la tarea a cancelar: ");
					controlador.cancelarTarea(id);
					break;
				case 7:
					id = leerEntero("Id de la tarea a finalizar: ");
					controlador.finalizarTarea(id);
					break;
				case 8:
					id = leerEntero("Id de la tarea: ");
					controlador.verVoluntariosTarea(id);
					break;
				case 9:
					feedback = leerCadena("feedback del voluntario: ");
					controlador.darFeedbackDeVoluntarios(feedback, leerEntero("ID de la tarea: "), leerEntero("ID del voluntario: "));
					break;
				case 10:
					id = leerEntero("Id del voluntario:");
					darAptitud(id);
					break;
				case 11:
					System.out.println(organizacion.toString());
					break;
				case 12:
					System.out.println("Saliendo...");
					controlador.salir();
					break;
			}
		} while (opcion != 12);
	}
	private static void imprimirMenuOrganizacion() {
		System.out.println("1) Ver proyectos");
		System.out.println("2) Crear proyecto");
		System.out.println("3) Ver tareas de un proyecto");
		System.out.println("4) Crear tarea");
		System.out.println("5) Empezar una tarea");
		System.out.println("6) Finalizar una tarea.");
		System.out.println("7) Cancelar una tarea");
		System.out.println("8) Ver los voluntarios en una tarea");
		System.out.println("9) Dar feedback de los voluntarios");
		System.out.println("10) Dar aptitud a un voluntario");
		System.out.println("11) Ver mi informacion");
		System.out.println("12) Salir");
	}

	/*
	 *		Metodos de lectura de datos
	 */
	public static String leerCadena(String msg) {
		System.out.println(msg);
		return teclado.nextLine();
	}	
	public static int leerEntero(String msg) {
		System.out.println(msg);
		return Integer.parseInt(teclado.nextLine());
	}
	public static boolean leerBoolean(String msg) {
		String respuesta;
		boolean salir = false;
		boolean devolver = false;
		do {
			System.out.println(msg + ("(S/N): "));
			respuesta = teclado.nextLine();
			if (respuesta.equals("n") || respuesta.equals("N")) {
				salir = true;
				devolver = false;
			} else if (respuesta.equals("s") || respuesta.equals("S")) {
				salir = true;
				devolver = true;
			}
		} while (!salir);
	
		return devolver;
	}
	
	/*
	 *		Otros metodos
	 */
	private static void darAptitud(int idVoluntario) {
		int opcion, idAptitud;
		String nombre, descripcion;
		boolean adquirible;
		Aptitud a;		
		
		do {
			opcion = leerEntero("1) Crear aptitud\n2) dar Aptitud existente\nopcion: ");
		}
		while (opcion == 2 || opcion == 1);
		
		if (opcion == 1) {
			nombre = leerCadena("nombre de la aptitud: ");
			descripcion = leerCadena("descripcion de la aptitud: ");
			adquirible = leerBoolean("¿Adquirible? ");
			
			a = controlador.crearAptitud(nombre, descripcion, adquirible);
			
			controlador.darAptitudVoluntario(idVoluntario, a.getId());
		} else {
			idAptitud = leerEntero("Id de la aptitud: ");
			controlador.darAptitudVoluntario(idVoluntario, idAptitud);
		}
		
	}
}



