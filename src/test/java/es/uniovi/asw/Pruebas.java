package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import es.uniovi.asw.parser.writer.TXTLetter;
import es.uniovi.asw.parser.writer.WordLetter;

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
	private Calendar c1 = GregorianCalendar.getInstance();
	private File file;

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
		file = new File("Log");
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
	 * Inicializamos los parámetros que usamos en los test.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws CitizenException
	 */
	@Before
	public void inicializarTest()
			throws NoSuchAlgorithmException, CitizenException {
		System.setErr(new PrintStream(errContent));
		c1.set(Calendar.YEAR, 1988);
		c1.set(Calendar.MONTH, Calendar.JANUARY);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		usuario = new Citizen(8, "a", "b b", "c@gmail.com",
				new Date(c1.getTimeInMillis()), "residencia", "nacionalidad",
				"dni");
	}

	/**
	 * 
	 */
	@After
	public void finalizarTest() {
		System.setErr(null);
		exception = null;
	}

	/**
	 * Test que comprueba el correcto funcionamiento de la clase LoadUsers
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

	/**
	 * Test que comprueba el correcto funcionamiento de la clase RCitizens.
	 * 
	 * @throws CitizenException
	 */
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

	/**
	 * Test que comprueba el correcto funcionamiento de la clase LoadFromExcel.
	 * 
	 * @throws CitizenException
	 */
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

	/**
	 * Test que comprueba el correcto funcionamiento de la clase PDFLetter
	 * 
	 * @throws CitizenException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testWritterPDF()
			throws CitizenException, NoSuchAlgorithmException {
		Letter carta = new PDFLetter();
		assertNotNull(carta);
		testTemplate(carta);
		file = new File("..\\citizensLoader4b\\Letter\\PDF\\" + usuario.getDni()
				+ ".pdf");
		assertTrue(file.exists());

	}

	/**
	 * Test que comprueba el correcto funcionamiento de la clase testWritterTXT
	 * 
	 * @throws CitizenException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testWritterTXT()
			throws CitizenException, NoSuchAlgorithmException {
		Letter carta = new TXTLetter();
		assertNotNull(carta);
		testTemplate(carta);
		file = new File("..\\citizensLoader4b\\Letter\\TXT\\" + usuario.getDni()
				+ ".txt");
		assertTrue(file.exists());
	}

	/**
	 * Test que comprueba el correcto funcionamiento de la clase WordLetter
	 * 
	 * @throws CitizenException
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void testWritterWord()
			throws CitizenException, NoSuchAlgorithmException {
		Letter carta = new WordLetter();
		assertNotNull(carta);
		testTemplate(carta);
		file = new File("..\\citizensLoader4b\\Letter\\WORD\\"
				+ usuario.getDni() + ".docx");
		assertTrue(file.exists());
	}

	/**
	 * Testea todas las acciones comunes de los objetos de tipo Letter
	 * 
	 * @param carta
	 *            Letter que queremos comprobar
	 */
	private void testTemplate(Letter carta) {
		// Prueba con un usuario correcto
		try {
			carta.generateLetter(usuario);
		} catch (Exception e) {
			exception = e;
		}
		assertNull(exception);

		// Prueba con un usuario null
		try {
			carta.generateLetter(null);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("Se ha pasado un null como parámetro.",
				exception.getMessage());

		// Prueba con un usuario sin ningun parámetro iniciado
		Citizen anonimo = new Citizen();
		try {
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("El siguiente campo del usuario esta vacío -> ID",
				exception.getMessage());

		// Vamos comprobando con el usuario sin parámetro pero cada vez vamos
		// añadiendo uno para ver por donde puede fallar.
		try {
			anonimo.setId(9);
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("No todos los campos estan inicializados",
				exception.getMessage());

		try {
			anonimo.setApellidos("");
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("El siguiente campo del usuario esta vacío -> Apellidos",
				exception.getMessage());

		try {
			anonimo.setApellidos("a");
			anonimo.setDni("");
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("El siguiente campo del usuario esta vacío -> DNI",
				exception.getMessage());

		try {
			anonimo.setDni("a");
			anonimo.setEmail("");
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("El siguiente campo del usuario esta vacío -> Email",
				exception.getMessage());

		try {
			anonimo.setEmail("a");
			anonimo.setFechaNacimiento(null);
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("La fecha de nacimiento no puede ser null.",
				exception.getMessage());

		try {
			anonimo.setFechaNacimiento(new Date(c1.getTimeInMillis()));
			anonimo.setNacionalidad("");
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals(
				"El siguiente campo del usuario esta vacío -> Nacionalidad",
				exception.getMessage());

		try {
			anonimo.setNacionalidad("a");
			anonimo.setNombre("");
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("El siguiente campo del usuario esta vacío -> Nombre",
				exception.getMessage());

		try {
			anonimo.setNombre("a");
			anonimo.setPassword(null);
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("No se puede encriptar una contraseña nula",
				exception.getMessage());

		try {
			anonimo.setPassword("");
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("No todos los campos estan inicializados",
				exception.getMessage());

		try {
			anonimo.setPassword("sadf");
			anonimo.setResidencia("");
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("El siguiente campo del usuario esta vacío -> Residencia",
				exception.getMessage());

		try {
			anonimo.setResidencia("a");
			carta.generateLetter(anonimo);
			exception = null;
		} catch (Exception e) {
			exception = e;
		}
		assertNull(exception);

		// Prueba con un usuario con ID negativo
		try {
			anonimo.setId(-25);
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("El ID es menor que 0", exception.getMessage());

		// Prueba con un usuario que todavía no ha nacido
		try {
			c1.set(2056, 12, 12);
			anonimo.setFechaNacimiento(new Date(c1.getTimeInMillis()));
			carta.generateLetter(anonimo);
		} catch (Exception e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("La fecha de nacimiento es posterior al dia actual.",
				exception.getMessage());
	}
}
