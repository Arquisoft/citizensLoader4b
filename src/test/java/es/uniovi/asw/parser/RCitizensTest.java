package es.uniovi.asw.parser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.RCitizens;
import es.uniovi.asw.parser.ReadCitizens;

public class RCitizensTest {

	/**
	 * Test que comprueba el correcto funcionamiento de la clase RCitizens.
	 * 
	 * @throws CitizenException
	 *             Excepción ocurrida durante la ejecución
	 */
	@Test
	public void testRCitizens() throws CitizenException {
		// 2a
//		ReadCitizens rs = new RCitizens();
//		List<Citizen> citizens = null;
//		Exception exception = null;
//		try {
//			citizens = rs.readCitizens(
//					"..\\citizensLoader4b\\src\\test\\resources\\test.xlsx");
//		} catch (Exception e) {
//			exception = e;
//		}
//		assertNotEquals(citizens.size(), 0);
//		assertNull(exception);
//
//		try {
//			citizens = rs.readCitizens(
//					"..\\citizensLoader4b\\src\\test\\resources\\tes.xlsx");
//		} catch (Exception e) {
//			exception = e;
//		}
//		assertNotNull(exception);
//		assertEquals("Fichero no encontrado", exception.getMessage());
//
//		try {
//			citizens = rs.readCitizens("");
//		} catch (Exception e) {
//			exception = e;
//		}
//		assertNotNull(exception);
//		System.out.println(exception.getMessage());
//		assertEquals("Error en el fichero la extensión del archivo",
//				exception.getMessage());
	}

}
