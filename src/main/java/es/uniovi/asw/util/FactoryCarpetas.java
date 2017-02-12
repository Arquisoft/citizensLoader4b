package es.uniovi.asw.util;

import java.io.File;

public class FactoryCarpetas {
	public static void crearCarpeta(String nombreCarpeta) {
		String nombre = "..\\citizensLoader4b\\" + nombreCarpeta;
		File file = new File(nombre);
		if (!file.exists()) {
			file.mkdir();
		}
	}
}
