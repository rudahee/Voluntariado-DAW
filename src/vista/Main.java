package vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import controlador.Controller;
import excepciones.LimiteFechaException;
import excepciones.cantidadVoluntariosException;
import modelo.Aptitud;
import modelo.Organizacion;
import modelo.Voluntario;

public class Main {

	static Scanner teclado = new Scanner(System.in);
	static Controller controlador = new Controller();

	public static void main(String[] args) throws ParseException, LimiteFechaException, cantidadVoluntariosException {
		// Pregunta para acceder al menu de voluntario o de organizacion.

		int usuario;

		usuario = leerEntero("Seleccione la opcion\n1) Organizacion\n2) Voluntario\n\n>> ");

		if (usuario == 1) {
			menuOrganizacion();

		} else {
			menuVoluntario();
		}

	}

	/*
	 * METODOS DE VOLUNTARIO
	 * 
	 */
	private static void menuVoluntario() {
		int opcion = 0;
		int existe, id;
		int idVoluntario = 0;
		Voluntario v;
		String feedback;

		// Se pregunta si usar un voluntario existente o crear uno nuevo
		existe = leerEntero("Selecciona la opcion:\n1) Tengo una cuenta existente\n2) Registrarme");

		if (existe == 1) {
			do {
				// Pedimos el id, hasta que sea uno que correcto.
				idVoluntario = leerEntero("ID: ");
			} while (controlador.comprobarUsuario(idVoluntario));

			v = controlador.obtenerVoluntario(idVoluntario);

			// Mostramos los datos del voluntario elegido
			System.out.println("\n" + v.toString() + "\n");

		} else {
			String nombre = leerCadena("Nombre: ");
			String apellidos = leerCadena("Apellidos: ");

			// Creamos el voluntario
			v = controlador.crearVoluntario(nombre, apellidos);

			// Imprimimos los datos del voluntario
			System.out.println("\n" + v.toString() + "\n");
		}

		do {
			// Imprimir las cadenas con las opciones de los voluntarios.
			imprimirMenuVoluntario();

			// Las opciones validas son desde 1 a 8. Salir = 8
			opcion = leerEntero("opcion: ");

			switch (opcion) {
			case 1:
				// Ver Proyectos
				System.out.println(controlador.verProyectos());
				break;
			case 2:
				// Unirse a un proyecto
				id = leerEntero("Introduce el id del proyecto: ");
				controlador.unirseProyecto(v, id);
				break;
			case 3:
				// Ver tareas en un proyecto
				id = leerEntero("ID del proyecto: ");
				System.out.println(controlador.tareasEnProyecto(id));
				break;
			case 4:
				// Unirse a una tarea
				id = leerEntero("ID de la tarea: ");
				if (!controlador.unirseTarea(id, v)) {
					System.out.println("No se ha podido unir a la tarea.");
				}
				break;
			case 5:
				// Dar feedback de una tarea
				feedback = leerCadena("feedback del voluntario: ");
				controlador.darFeedbackDeTarea(feedback, leerEntero("ID de la tarea: "),
						leerEntero("ID del voluntario: "));
				break;
			case 6:
				// Agregar una Aptitud
				darAptitud(v.getId());
				break;
			case 7:
				// Ver informacion del voluntario
				System.out.println(v.toString());
				break;
			case 8:
				// Salir
				System.out.println("Adios");
				break;
			}
		} while (opcion != 8);
	}

	private static void imprimirMenuVoluntario() {
		// Mostrar las opciones validas para un voluntario

		System.out.println("1) Ver Proyectos");
		System.out.println("2) Unirse a un proyecto");
		System.out.println("3) Ver tareas en un proyecto");
		System.out.println("4) Unirse a una tarea");
		System.out.println("5) Dar feedback de una tarea");
		System.out.println("6) Agregar una Aptitud");
		System.out.println("7) Ver mi informacion");
		System.out.println("8) Salir");
	}

	/*
	 * METODOS DE ORGANIZACION
	 * 
	 */
	private static void menuOrganizacion() throws ParseException, LimiteFechaException, cantidadVoluntariosException {
		int opcion = 0;
		int existe, id, minimo, maximo;
		boolean individual;

		Organizacion organizacion;

		String nombre, descripcion, localizacion, feedback;
		Date fechaInicio = new Date(), fechaFinalizacion = new Date();
		SimpleDateFormat formatearFecha = new SimpleDateFormat("dd/MM/yyyy");

		// Se pregunta si usar una organizacion existente o crear una nueva
		existe = leerEntero("Selecciona la opcion:\n1) Tengo una cuenta existente\n2) Registrarme");

		if (existe == 1) {
			do {
				// Pedimos el id, hasta que sea uno que correcto.
				id = leerEntero("ID: ");
			} while (controlador.comprobarOrganizacion(id));

			organizacion = controlador.obtenerOrganizacion(id);

			// Mostramos los datos de la organizacion elegida
			System.out.println("\n" + organizacion.toString() + "\n");

		} else {
			String nombreOrganizacion = leerCadena("Nombre: ");

			// Creamos la organizacion
			organizacion = controlador.crearOrganizacion(nombreOrganizacion);

			// Mostramos los datos de la organizacion elegida
			System.out.println("\n" + organizacion.toString() + "\n");
		}

		do {
			// Imprimir las cadenas con las opciones de los voluntarios.
			imprimirMenuOrganizacion();

			// Las opciones validas son desde 1 a 12. Salir = 12
			opcion = leerEntero("opcion: ");

			switch (opcion) {
			case 1:
				// Ver proyectos
				System.out.println(controlador.verProyectosOrganizacion(organizacion));
				break;
			case 2:
				// Crear proyectos
				nombre = leerCadena("Nombre del proyecto: ");
				descripcion = leerCadena("Descripcion del proyecto: ");
				localizacion = leerCadena("Localizacion del proyecto: ");
				fechaInicio = formatearFecha.parse(leerCadena("Fecha de inicio:"));
				fechaFinalizacion = formatearFecha.parse(leerCadena("Fecha de finalizacion"));

				controlador.crearProyecto(nombre, descripcion, localizacion, fechaInicio, fechaFinalizacion,
						organizacion);
				break;
			case 3:
				// Ver tareas de un proyecto
				id = leerEntero("ID del proyecto: ");
				System.out.println(controlador.verTareas(id));
				break;
			case 4:
				// Crear tarea
				nombre = leerCadena("Introduce el nombre de la tarea: ");
				descripcion = leerCadena("Introduce la descripcion: ");
				localizacion = leerCadena("Introduce la localizacion: ");
				fechaInicio = formatearFecha.parse(leerCadena("Introduce la fecha de inicio: "));
				fechaFinalizacion = formatearFecha.parse(leerCadena("Introduce la fehca de finalizacion: "));
				minimo = leerEntero("Introduce el minimo de voluntarios: ");
				maximo = leerEntero("Introduce el maximo de voluntarios: ");
				individual = leerBoolean("Trabajo individual? ");
				id = leerEntero("Proyecto en el que crear la tarea: ");

				controlador.crearTarea(nombre, descripcion, fechaInicio, fechaFinalizacion, localizacion, maximo,
						minimo, individual, id);
				break;
			case 5:
				// Empezar una tarea
				id = leerEntero("Id de la tarea a empezar: ");
				controlador.empezarTarea(id);
				break;
			case 6:
				// Finalizar una tarea
				id = leerEntero("Id de la tarea a cancelar: ");
				controlador.cancelarTarea(id);
				break;
			case 7:
				// Cancelar una tarea
				id = leerEntero("Id de la tarea a finalizar: ");
				controlador.finalizarTarea(id);
				break;
			case 8:
				// Ver los voluntarios en una tarea
				id = leerEntero("Id de la tarea: ");
				System.out.println(controlador.verVoluntariosTarea(id));
				break;
			case 9:
				// Dar feedback de los voluntarios
				feedback = leerCadena("feedback del voluntario: ");
				controlador.darFeedbackDeVoluntarios(feedback, leerEntero("ID de la tarea: "),
						leerEntero("ID del voluntario: "));
				break;
			case 10:
				// Dar aptitud a un voluntario
				id = leerEntero("Id del voluntario:");
				darAptitud(id);
				break;
			case 11:
				// Ver informacion de la organizacion activa
				System.out.println(organizacion.toString());
				break;
			case 12:
				// Salir
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
		System.out.println("6) Finalizar una tarea");
		System.out.println("7) Cancelar una tarea");
		System.out.println("8) Ver los voluntarios en una tarea");
		System.out.println("9) Dar feedback de los voluntarios");
		System.out.println("10) Dar aptitud a un voluntario");
		System.out.println("11) Ver mi informacion");
		System.out.println("12) Salir");
	}

	/*
	 * Metodos de lectura de datos
	 * 
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
	 * Otros metodos
	 * 
	 */
	private static void darAptitud(int idVoluntario) {
		int opcion, idAptitud;
		String nombre, descripcion;
		boolean adquirible;
		Aptitud aptitud;

		do {
			// Preguntamos si creamos una aptitud o no.
			opcion = leerEntero("1) Crear aptitud\n2) dar Aptitud existente\nopcion: ");
		} while (opcion != 2 && opcion != 1);

		if (opcion == 1) {
			nombre = leerCadena("nombre de la aptitud: ");
			descripcion = leerCadena("descripcion de la aptitud: ");
			adquirible = leerBoolean("¿Adquirible? ");

			// Creamos la aptitud
			aptitud = controlador.crearAptitud(nombre, descripcion, adquirible);

			// Y llamamos al controlador para que efectue las operaciones necesarias
			controlador.darAptitudVoluntario(idVoluntario, aptitud.getId());
		} else {

			// Usamos una aptitud existente
			idAptitud = leerEntero("Id de la aptitud: ");
			controlador.darAptitudVoluntario(idVoluntario, idAptitud);
		}
	}
}
