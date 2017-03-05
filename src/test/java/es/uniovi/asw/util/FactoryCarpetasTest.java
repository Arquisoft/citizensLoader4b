package es.uniovi.asw.util;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import es.uniovi.asw.LoadUsers;
import es.uniovi.asw.common.CitizenException;

public class FactoryCarpetasTest {

	/**
	 * Test que comprueba que se crean correctamente las carpetas que contiene
	 * el log de fallos y las cartas dirigidas a los usuarios respectivamente.
	 * 
	 * @throws CitizenException
	 *             Excepción ocurrida durante la ejecución
	 */

	@Test
	public void testCreacionCarpetas() throws CitizenException {
		LoadUsers.main();
		// Compramos la correcta creación del Log
		File file = new File("Log");
		assertTrue(file.exists());
		assertTrue(file.isDirectory());

		// Comprobamos la correcta creación de las cartas
		file = new File("Letter");
		assertTrue(file.exists());
		assertTrue(file.isDirectory());
		File[] tiposCarta = file.listFiles();
		assertEquals(3, tiposCarta.length);
		assertEquals("PDF", tiposCarta[0].getName());
		assertEquals("TXT", tiposCarta[1].getName());
		assertEquals("WORD", tiposCarta[2].getName());
	}

}
