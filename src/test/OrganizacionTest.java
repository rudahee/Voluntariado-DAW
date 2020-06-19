package test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelo.Organizacion;
import modelo.Proyecto;

class OrganizacionTest {
	
	static Organizacion o;
	static Proyecto p;
	
	@BeforeAll
	public static void init() throws ParseException {
		int idOrganizacion = 01, idProyecto = 04;
		String nombreOrganizacion = "Organiz01", nombreProyecto = "Proyect04", 
			descripcionProyecto = "descripcion 04", localizacionProyecto = "Localizacion 04";
		SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy"); 
		Date fechaInicio = sfd.parse("01/01/1990"), fechaFinalizacion = sfd.parse("01/01/2000");
			
		
		o = new Organizacion();
		o.setId(idOrganizacion);
		o.setNombre("nombre 01");
		p = new Proyecto();
		p.setId(idProyecto);
		p.setDescripcion(descripcionProyecto);
		p.setLocalizacion(localizacionProyecto);
		p.setNombre(nombreProyecto);
		p.setFechaInicio(fechaInicio);
		p.setFechaFinalizacion(fechaFinalizacion);
	}
	
	@Test
	void testAddProyecto() {
		int tamanho = o.getListaProyectos().size();
		
		o.addProyecto(p);
		assertEquals(tamanho + 1, o.getListaProyectos().size());
	}

}
