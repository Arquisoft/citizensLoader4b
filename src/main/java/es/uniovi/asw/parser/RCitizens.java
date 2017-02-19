package es.uniovi.asw.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public class RCitizens implements ReadCitizens {

	@Override
	public List<Citizen> readCitizens(File fichero) throws CitizenException {
		List<Citizen> citizens;
		String[] analizador;
		Parser leer = null;
		citizens = new ArrayList<Citizen>();
		List<Citizen> aux = new ArrayList<Citizen>();
		try {
			analizador = fichero.getName().split(Pattern.quote("."));
			if (citizens.isEmpty()) {
				if (analizador[analizador.length - 1].equals("xlsx")) {
					leer = new LoadFromExcel();
				} else {
					throw new CitizenException(
							"Error en el fichero la extensión del archivo");
				}
				citizens = leer.loadUsers(fichero);
			} else {
				aux = leer.loadUsers(fichero);
			}
			for (int j = 0; j < aux.size(); j++)
				citizens.add(aux.get(j));

		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CitizenException("No se ha especificado la ruta de "
					+ "acceso al archivo correctamente.");
		}

		return citizens;

	}
}
