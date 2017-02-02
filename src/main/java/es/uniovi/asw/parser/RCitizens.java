package es.uniovi.asw.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public class RCitizens implements ReadCitizens {

	@Override
	public List<Citizen> readCitizens(String... args) throws CitizenException {
		String[] analizador;

		Parser leer = null;
		List<Citizen> citizens = new ArrayList<Citizen>();
		List<Citizen> aux = new ArrayList<Citizen>();

		for (int i = 0; i < args.length; i++) {
			analizador = args[i].split(Pattern.quote("."));
			if (citizens.isEmpty()) {
				if (analizador[3].equals("xlsx"))
					leer = new LoadFromExcel();
				else
					System.out.println(
							"Error en el fichero la extensión del archivo");
				citizens = leer.loadUsers(args[i]);
			} else {
				aux = leer.loadUsers(args[i]);
			}

			for (int j = 0; j < aux.size(); j++)
				citizens.add(aux.get(j));
		}

		return citizens;
	}

}
