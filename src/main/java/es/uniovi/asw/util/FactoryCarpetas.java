package es.uniovi.asw.util;

import java.io.File;

public class FactoryCarpetas {
	public static void crearCarpeta(String nombreCarpeta) {
		File file = new File(nombreCarpeta);

		if (!file.exists()) {
			file.mkdir();
		}
	}
}
