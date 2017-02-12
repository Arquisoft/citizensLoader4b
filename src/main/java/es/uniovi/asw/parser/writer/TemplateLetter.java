package es.uniovi.asw.parser.writer;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public abstract class TemplateLetter implements Letter {
	private String tipo;

	@Override
	public void generateLetter(Citizen citizen) throws CitizenException {
		tipo = indicarTipo();
		comprobarUsuario(citizen);
		try {
			FactoryLetter.generate(tipo);
			crearCarta(citizen);
		} catch (DocumentException e) {
			throw new CitizenException(
					"[DocumentException] No se ha podido generar "
							+ "la carta en [" + tipo + "] para el usuario "
							+ citizen.getDni());
		} catch (IOException e) {
			throw new CitizenException(
					"[IOException] No se ha podido generar " + "la carta en ["
							+ tipo + "] para el usuario " + citizen.getDni());
		} finally {
			try {
				cerrarCarta(citizen);
			} catch (IOException e2) {
				throw new CitizenException("[ERROR] [" + tipo + "] "
						+ citizen.getDni() + ": " + e2.getMessage());
			}
		}
	}

	private void comprobarUsuario(Citizen citizen) throws CitizenException {
		String mensaje = "El siguiente campo del usuario esta vacío -> ";
		try {
			if (citizen == null) {
				throw new CitizenException(
						"Se ha pasado un null como parámetro.");
			}  if (citizen.getId() == 0) {
				throw new CitizenException(mensaje + "ID");
			} else if (citizen.getApellidos().isEmpty()) {
				throw new CitizenException(mensaje + "Apellidos");
			} else if (citizen.getDni().isEmpty()) {
				throw new CitizenException(mensaje + "DNI");
			} else if (citizen.getEmail().isEmpty()) {
				throw new CitizenException(mensaje + "Email");
			} else if (citizen.getFechaNacimiento().equals(null)) {
				throw new CitizenException(mensaje + "Fecha de nacimiento");
			} else if (citizen.getNacionalidad().isEmpty()) {
				throw new CitizenException(mensaje + "Nacionalidad");
			} else if (citizen.getNombre().isEmpty()) {
				throw new CitizenException(mensaje + "Nombre");
			} else if (citizen.getPassword().isEmpty()) {
				throw new CitizenException(mensaje + "Password");
			} else if (citizen.getResidencia().isEmpty())
				throw new CitizenException(mensaje + "Residencia");
		} catch (NullPointerException e) {
			throw new CitizenException(
					"No todos los campos estan inicializados");
		}
	}

	protected abstract String indicarTipo();

	protected abstract void crearCarta(Citizen citizen)
			throws FileNotFoundException, DocumentException, IOException;

	protected abstract void cerrarCarta(Citizen citizen) throws IOException;

}
