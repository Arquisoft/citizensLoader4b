package es.uniovi.asw.business.impl.classes;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.model.exception.CitizenException;
import es.uniovi.asw.parser.ReadList;
import es.uniovi.asw.parser.impl.RList;
import es.uniovi.asw.util.FactoryCarpetas;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by igm1990 on 15/03/2017.
 */
public class CitizenFind {
	public List<Citizen> findAll()
			throws CitizenException, NoSuchAlgorithmException {
		return run();
	}

	private List<Citizen> run() throws CitizenException {
		String directorio = "archivosExcel";
		File f = new File(directorio);
		List<Citizen> citizens = new ArrayList<>();
		if (f.exists()) {
			File[] ficheros = f.listFiles();
			if (ficheros.length == 0) {
				System.out.println("Consulte el manual de uso con -help.");
			} else {
				for (int i = 0; i < ficheros.length; i++) {
					citizens = leerFichero(ficheros[i]);
				}
			}
		} else {
			new FactoryCarpetas().crearCarpeta("archivosExcel");
			System.out.println("Consulte el manual de ayuda con -help.");
			throw new CitizenException(
					"No se encuentra la carpeta archivosExcel."
							+ "\nSe ha creado automáticamente.");
		}
		return citizens;

	}

	private List<Citizen> leerFichero(File fichero) throws CitizenException {
		ReadList leer = new RList();
		return leer.readCitizens(fichero);
	}
}
