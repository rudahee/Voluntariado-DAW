package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.Recurso;
import modelo.Tarea;

class TareaTest {
	static Tarea t;
	static Recurso r;
	
	@BeforeAll
	public static void init() {
		r = new Recurso();
		t = new Tarea();
	}
	@Test
	void testAddRecurso() {
		int cantidadRecursos = t.getListaRecursos().size();
		t.addRecurso(r);
		assertEquals(cantidadRecursos +1, t.getListaRecursos().size());
	}	

}
