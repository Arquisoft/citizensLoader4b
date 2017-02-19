package es.uniovi.asw.parser;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.LoadFromExcel;
import es.uniovi.asw.parser.Parser;

public class LoadFromExcelTest {

	/**
	 * Test que comprueba el correcto funcionamiento de la clase LoadFromExcel.
	 * 
	 * @throws CitizenException
	 *             Excepción ocurrida durante la ejecución
	 */
	@Test
	public void testLoadFromExcel() throws CitizenException {
		Parser parser = new LoadFromExcel();
		List<Citizen> citizens = null;
		Exception exception = null;
		try {
			citizens = parser.loadUsers(
					"..\\citizensLoader4b\\src\\test\\resources\\test.xlsx");
		} catch (Exception e) {
			exception = e;
		}
		assertNotEquals(citizens.size(), 0);
		assertNull(exception);

		try {
			citizens = parser.loadUsers(
					"..\\citizensLoader4b\\src\\test\\resources\\tet.xlsx");
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("Fichero no encontrado", exception.getMessage());

		try {
			citizens = parser.loadUsers("");
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("Fichero no encontrado", exception.getMessage());
	}
}
