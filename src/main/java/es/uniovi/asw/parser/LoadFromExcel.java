package es.uniovi.asw.parser;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public class LoadFromExcel implements Parser {
	/**
	 * Leemos los parametros del excel y lo vamos almacenando en una lista de
	 * ciudadanos que serán los que metamos en la BBDD.
	 * 
	 * @param fichero
	 *            fichero de tipo excel
	 * @return retorna una lista con todos los ciudadanos que se han incluido en
	 *         el fichero
	 * @throws IOException
	 */
	public List<Citizen> loadUsers(String fichero) throws CitizenException {

		List<Citizen> citizens = new ArrayList<Citizen>();
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(fichero));
		} catch (FileNotFoundException e) {
			throw new CitizenException("Fichero no encontrado");
		}

		// Obtenemos la hoja de votantes del censo
		XSSFWorkbook patron;
		try {
			patron = new XSSFWorkbook(file);
		} catch (IOException e) {
			throw new CitizenException(
					"No hemos obtenido la hoja de votantes del censo");

		}

		// Obtenemos la primera hoja del libro excel
		XSSFSheet hoja = patron.getSheetAt(0);

		// Iteramos sobre cada fila de la primera hoja
		Iterator<Row> rowIterator = hoja.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// Para cada fila, iteramos a través de cada una de sus columnas
			Iterator<Cell> columnas = row.cellIterator();
			loadDataCitizen(columnas, citizens); // Cargamos los datos del
													// votante
		}
		try {
			patron.close();
		} catch (IOException e) {
			throw new CitizenException(
					"No se ha podido cerrar la hoja del libro excel.");
		}
		return citizens;
	}

	/**
	 * Metodo que crea el Citizen y lo almacena en la lista
	 * 
	 * @param columnas
	 *            son los datos de cada fila e iteramos por columna para obtener
	 *            cada dato
	 * @param citizens
	 *            lista donde se va a almacenar cada Citizen
	 */
	// Este id cuando tengamos la BBDD habra que obtenerlo de ahi, ya que es el
	// ultimo que añadamos.
	private long id = 0;

	private void loadDataCitizen(Iterator<Cell> columnas,
			List<Citizen> citizens) throws CitizenException {
		String nombre = null, apellidos = null, dni, email = null, residencia,
				nacionalidad;
		java.sql.Date fechaNacimiento;
		Citizen citizen = null;

		try {
			nombre = columnas.next().getStringCellValue();
			apellidos = columnas.next().getStringCellValue();
			email = columnas.next().getStringCellValue();
			fechaNacimiento = new java.sql.Date(
					columnas.next().getDateCellValue().getTime());
			residencia = columnas.next().getStringCellValue();
			nacionalidad = columnas.next().getStringCellValue();
			dni = columnas.next().getStringCellValue();

			citizen = new Citizen(id++, nombre, apellidos, email,
					fechaNacimiento, residencia, nacionalidad, dni);
			citizens.add(citizen);
		} catch (Exception ne) {
			throw new CitizenException("Erro en el archivo. ");
		}
	}
}
