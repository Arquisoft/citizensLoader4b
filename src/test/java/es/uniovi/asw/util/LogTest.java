package es.uniovi.asw.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import es.uniovi.asw.ReportWritter.WreportP;
import es.uniovi.asw.common.CitizenException;

public class LogTest {
	private Exception exception;

	@After
	public void finalizarTest() {
		exception = null;
	}

	@Test
	public void grabarNull() throws CitizenException {
		try {
			new WreportP().grabarError(null);
		} catch (Exception e) {
			exception = e;
		}
		assertEquals(CitizenException.class, exception.getClass());
		assertEquals("El error a guardar en el fichero Log no puede ser null.",
				exception.getMessage());

	}

	@Test
	public void grabarFalloVacio() {
		try {
			new WreportP().grabarError("");
		} catch (Exception e) {
			exception = e;
		}
		assertEquals(CitizenException.class, exception.getClass());
		assertEquals("El error a guardar en el fichero Log no puede ser vacio.",
				exception.getMessage());
	}

	@Test
	public void grabarFallo() {
		try {
			new WreportP().grabarError("Prueba de fallo");
		} catch (Exception e) {
			exception = e;
		}
		assertNull(exception);
	}

}
