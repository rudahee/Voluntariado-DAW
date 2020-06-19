package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.Tarea;
import modelo.Voluntario;
import modelo.VoluntarioTarea;

class VoluntarioTareaTest {
	
	static Voluntario v;
	static Tarea t;
	static VoluntarioTarea vt;
	
	@BeforeAll
	public static void init() {
		v = new Voluntario();
		t = new Tarea();
		vt = new VoluntarioTarea(t, v);
	}
	
	@Test
	void setFeedbackDeVoluntarioTest() {
		String feedbackDeVoluntario = new String("feedback del voluntario");
		vt.setFeedbackDeVoluntario(feedbackDeVoluntario);
		assertEquals(feedbackDeVoluntario, vt.getFeedbackDeVoluntario());
	}
	
	@Test
	void setFeedbackDeTareaTest() {
		String feedbackDeTarea = new String("feedback de la tarea");
		vt.setFeedbackDeTarea(feedbackDeTarea);
		assertEquals(feedbackDeTarea, vt.getFeedbackDeTarea());
	}
}
