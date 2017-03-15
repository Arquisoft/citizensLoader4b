package es.uniovi.asw;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import es.uniovi.asw.util.CitizenException;

@RunWith(SpringJUnit4ClassRunner.class)
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
		errContent.reset();
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
		LoadUsers.main(new String[] {});
		assertEquals("", errContent.toString());
	}
}
