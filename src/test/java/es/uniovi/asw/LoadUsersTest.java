package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.common.CitizenException;

public class LoadUsersTest {

	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	/**
	 * Inicializamos los parámetros que usamos en los test.
	 * 
	 * @throws NoSuchAlgorithmException
	 *             Excepción ocurrida durante la encriptación
	 * @throws CitizenException
	 *             Excepción ocurrida durante la ejecución
	 */
	@Before
	public void inicializarTest()
			throws NoSuchAlgorithmException, CitizenException {
		System.setErr(new PrintStream(errContent));
	}

	/**
	 * Reiniciamos los parámetros de los test
	 */
	@After
	public void finalizarTest() {
		System.setErr(null);
	}

	/**
	 * Test que comprueba el correcto funcionamiento de la clase LoadUsers
	 * 
	 * @throws CitizenException
	 *             Excepción ocurrida durante la ejecución
	 */
	@Test
	public void testLecturaCorrecta() throws CitizenException {
		// 1a
		// Ruta correcta
		LoadUsers.main("..\\citizensLoader4b\\src\\test\\resources\\test.xlsx");
		assertEquals("", errContent.toString());
	}

	@Test
	public void testLecturaRutaErronea() throws CitizenException {
		LoadUsers.main("..\\citizb\\src\\test\\resources\\test.xlxs");
		assertEquals("Error en el fichero la extensión del archivo",
				errContent.toString());
	}

	@Test
	public void testLecturaRutaVacia() throws CitizenException {
		LoadUsers.main();
		assertEquals(
				"No se ha especificado la ruta de acceso al archivo correctamente.",
				errContent.toString());
		errContent.reset();
	}

}
