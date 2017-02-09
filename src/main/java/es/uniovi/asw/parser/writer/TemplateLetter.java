package es.uniovi.asw.parser.writer;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public abstract class TemplateLetter implements Letter {
	
	@Override
	public void generateLetter(Citizen citizen) throws CitizenException {
		String tipo = indicarTipo();
		try {
			FactoryLetter.generate(tipo);
			crearCarta(citizen);
		} catch (DocumentException e) {
			throw new CitizenException(
					"[ERROR] No se ha podido generar la carta en [" + tipo
							+ "] para el usuario " + citizen.getDni());
		} catch (IOException e) {
			throw new CitizenException(
					"[ERROR] No se ha podido generar la carta en [" + tipo
							+ "] para el usuario " + citizen.getDni());
		} finally {
			try {
				cerrarCarta(citizen);
			} catch (IOException e2) {
				throw new CitizenException("[ERROR] [" + tipo + "] "
						+ citizen.getDni() + ": " + e2.getMessage());
			}
		}
	}

	protected abstract String indicarTipo();

	protected abstract void crearCarta(Citizen citizen)
			throws FileNotFoundException, DocumentException, IOException;

	protected abstract void cerrarCarta(Citizen citizen) throws IOException;

}
