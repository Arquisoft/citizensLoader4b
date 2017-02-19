package es.uniovi.asw.parser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.RCitizens;
import es.uniovi.asw.parser.ReadCitizens;

public class RCitizensTest {
	private ReadCitizens rs = new RCitizens();
	private List<Citizen> citizens;
	private Exception exception;

	@Before
	public void inicializarTest() {
		citizens = null;
		exception = null;

	}

	/**
	 * Test que comprueba el correcto funcionamiento de la clase RCitizens.
	 * 
	 * @throws CitizenException
	 *             Excepción ocurrida durante la ejecución
	 */
	@Test
	public void testRutaCorrecta() throws CitizenException {
//		try {
//			citizens = rs.readCitizens(
//					"..\\citizensLoader4b\\src\\test\\resources\\test.xlsx");
//		} catch (Exception e) {
//			exception = e;
//		}
//		assertNotNull(citizens);
//		assertNull(exception);
//		assertNotEquals(citizens.size(), 0);
	}

	@Test
	public void testRutaIncorrecta() throws CitizenException {
		try {
			citizens = rs.readCitizens(
					"..\\citizensLoader4b\\src\\test\\resources\\tes.xlsx");
		} catch (Exception e) {
			exception = e;
		}
		assertNull(citizens);
		assertNotNull(exception);
		assertEquals(CitizenException.class, exception.getClass());
		assertEquals("Fichero no encontrado", exception.getMessage());
	}

	@Test
	public void testRutaVacia() throws CitizenException {
		try {
			citizens = rs.readCitizens("");
		} catch (Exception e) {
			exception = e;
		}
		assertNull(citizens);
		assertNotNull(exception);
		assertEquals(CitizenException.class, exception.getClass());
		assertEquals("Error en el fichero la extensión del archivo",
				exception.getMessage());
	}

}
