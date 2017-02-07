package PruebasTest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import es.uniovi.asw.LoadUsers;
import es.uniovi.asw.common.CitizenException;

/**
 * Clase test para comprobar el correcto funcionamiento de la aplicación.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Pruebas {

	/**
	 * Test que comprueba que se crean correctamente las carpetas que contiene
	 * el log de fallos y las cartas dirigidas a los usuarios respectivamente.
	 * 
	 * @throws CitizenException
	 */

	/*
	 * @Test public void testCreacionCarpetas() throws CitizenException {
	 * LoadUsers.main("..\\citizensLoader4b\\src\\test\\resources\\test.xlsx");
	 * 
	 * // Compramos la correcta creación del Log File file = new File("Log");
	 * assertTrue(file.exists()); assertTrue(file.isDirectory());
	 * 
	 * // Comprobamos la correcta creación de las cartas file = new
	 * File("Letter"); assertTrue(file.exists());
	 * assertTrue(file.isDirectory()); File[] tiposCarta = file.listFiles();
	 * assertEquals(3, tiposCarta.length); assertEquals("PDF",
	 * tiposCarta[0].getName()); assertEquals("TXT", tiposCarta[1].getName());
	 * assertEquals("WORD", tiposCarta[2].getName()); }
	 */

	/**
	 * Test que comprueba que funciona el lector de excel si indicamos bien la
	 * dirección.
	 * 
	 * @throws CitizenException
	 */
	@Test
	public void test() throws CitizenException {
		Exception ex = null;
		try {
			LoadUsers.main(
					"..\\citizensLoader4b\\src\\test\\resources\\test.xlsx");
		} catch (Exception e) {
			ex = e;
		}
		assertNull(ex);

		try {
			LoadUsers.main();
		} catch (Exception e) {
			ex = e;
		}
		assertNull(ex);

		try {
			LoadUsers.main("..\\citizb\\src\\test\\resources\\test.xlxs");
		} catch (Exception e) {
			assertEquals("Error en el fichero la extensión del archivo",
					e.getMessage());
		}
		
		try {
			LoadUsers.main("");
		} catch (Exception e) {
			assertEquals("Error en el fichero la extensión del archivo",
					e.getMessage());
		}

	}

}
