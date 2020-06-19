package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.Organizacion;
import modelo.Proyecto;
import modelo.Tarea;
import modelo.Voluntario;


class ProyectoTest {
	static Proyecto p;
	static Tarea t;
	static Voluntario v;
	
	@BeforeAll
	public static void init() {
		p = new Proyecto();
		v = new Voluntario();
		t = new Tarea();
	}
	
	@Test
	void testAddVoluntario() {
		int cantidadVoluntarios = p.getVoluntarios().size();
		p.addVoluntario(v);
		assertEquals(cantidadVoluntarios + 1, p.getVoluntarios().size());
	}

	@Test
	void testAddTarea() {
		int cantidadTareas = p.getListaTareas().size();
		p.addTarea(t);
		assertEquals(cantidadTareas + 1, p.getListaTareas().size());
	}

}
