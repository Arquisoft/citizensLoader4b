package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
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

	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setErr(new PrintStream(errContent));
	}

	/**
	 * Test que comprueba que se crean correctamente las carpetas que contiene
	 * el log de fallos y las cartas dirigidas a los usuarios respectivamente.
	 * 
	 * @throws CitizenException
	 */

	@Test
	public void testCreacionCarpetas() throws CitizenException {
		LoadUsers.main("..\\citizensLoader4b\\src\\test\\resources\\test.xlsx");
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

	/**
	 * Test que comprueba que funciona el lector de excel si indicamos bien la
	 * dirección.
	 * 
	 * @throws CitizenException
	 */
	@Test
	public void testLecturaExcel() throws CitizenException {
		// Ruta correcta
		LoadUsers.main("..\\citizensLoader4b\\src\\test\\resources\\test.xlsx");
		assertEquals("", errContent.toString());
		errContent.reset();

		// Si no indicamos ruta
		LoadUsers.main();
		assertEquals("No se ha especificado la ruta de acceso al archivo.",
				errContent.toString());
		errContent.reset();

		// Ruta no correcta
		LoadUsers.main("..\\citizb\\src\\test\\resources\\test.xlxs");
		assertEquals("Error en el fichero la extensión del archivo",
				errContent.toString());
		errContent.reset();
	}

	@After
	public void cleanUpStreams() {
		System.setErr(null);
	}
}
