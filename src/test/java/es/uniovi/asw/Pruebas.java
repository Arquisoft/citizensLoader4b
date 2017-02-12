package Pruebas;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.LoadUsers;
import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.parser.LoadFromExcel;
import es.uniovi.asw.parser.Parser;
import es.uniovi.asw.parser.RCitizens;
import es.uniovi.asw.parser.ReadCitizens;
import es.uniovi.asw.parser.writer.Letter;
import es.uniovi.asw.parser.writer.PDFLetter;

/**
 * Clase test para comprobar el correcto funcionamiento de la aplicación.
 * 
 * @author Iván González Mahagamage
 *
 */
public class Pruebas {
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	private List<Citizen> citizens;
	private Exception exception;
	private Citizen usuario;

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

	@SuppressWarnings("deprecation")
	@Before
	public void inicializarTest() throws NoSuchAlgorithmException {
		System.setErr(new PrintStream(errContent));
		usuario = new Citizen(8, "a", "b b", "c@gmail.com",
				new Date(2000, 2, 2), "residencia", "nacionalidad", "dni");
	}

	@After
	public void finalizarTest() {
		System.setErr(null);
		exception = null;
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

	@Test
	public void testRCitizens() throws CitizenException {
		ReadCitizens rs = new RCitizens();
		try {
			citizens = rs.readCitizens(
					"..\\citizensLoader4b\\src\\test\\resources\\test.xlsx");
		} catch (Exception e) {
			exception = e;
		}
		assertNotEquals(citizens.size(), 0);
		assertNull(exception);

		try {
			citizens = rs.readCitizens(
					"..\\citizensLoader4b\\src\\test\\resources\\tes.xlsx");
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("Fichero no encontrado", exception.getMessage());

		try {
			citizens = rs.readCitizens("");
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("No se ha especificado la ruta de acceso al "
				+ "archivo correctamente.", exception.getMessage());
	}

	@Test
	public void testLoadFromExcel() throws CitizenException {
		Parser parser = new LoadFromExcel();
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

	@Test
	public void testWritterPDF() throws CitizenException {
		Letter carta = new PDFLetter();
		try {
			carta.generateLetter(usuario);
		} catch (Exception e) {
			exception = e;
		}
		assertNull(exception);

		try {
			carta.generateLetter(null);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("Se ha pasado un null como parámetro.",
				exception.getMessage());

		Citizen anonimo = new Citizen();
		try {
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("El siguiente campo del usuario esta vacío -> ID",
				exception.getMessage());

		anonimo.setId(9);
		try {
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("No todos los campos estan inicializados",
				exception.getMessage());

	}
}
