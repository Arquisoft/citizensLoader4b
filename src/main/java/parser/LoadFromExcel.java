package parser;

import java.io.*;
import java.util.*;

import java.sql.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uniovi.asw.model.Citizen;

public class LoadFromExcel implements Parser{
	/**
	 * 
	 * @param fichero fichero de tipo excel
	 * @return retorna una lista con todos los ciudadanos que se han incluido en el fichero
	 * @throws IOException
	 */
	public List<Citizen> loadUsers( String fichero ) throws IOException {
		
		List<Citizen> citizens = new ArrayList<Citizen>();
		FileInputStream file = new FileInputStream( new File( fichero ) );
		
		//Obtenemos la hoja de votantes del censo
		XSSFWorkbook patron = new XSSFWorkbook( file ); 
		
		 //Obtenemos la primera hoja del libro excel
		XSSFSheet hoja = patron.getSheetAt(0);
		
		//Iteramos sobre cada fila de la primera hoja
		Iterator<Row> rowIterator = hoja.iterator(); 
	    rowIterator.next();
	    while( rowIterator.hasNext() ) {
	        Row row = rowIterator.next();
	        
	        //Para cada fila, iteramos a través de cada una de sus columnas
	        Iterator<Cell> columnas = row.cellIterator(); 
	        loadDataCitizen( columnas, citizens ); //Cargamos los datos del votante
	    }
	    patron.close();
		return citizens;
	}
	
	/**
	 * 
	 * @param file 
	 * @param columnas
	 * @param citizens
	 */
	private void loadDataCitizen( Iterator<Cell> columnas, List<Citizen> citizens ) {
		String nombre= null, apellidos = null, dni, email = null, residencia, nacionalidad;
		java.sql.Date fechaNacimiento;
		Citizen citizen = null;
		long i = 0;
		try{
			nombre = columnas.next().getStringCellValue();
			apellidos = columnas.next().getStringCellValue();
			email = columnas.next().getStringCellValue();
			fechaNacimiento = new java.sql.Date (columnas.next().getDateCellValue().getTime());
			residencia = columnas.next().getStringCellValue();
			nacionalidad = columnas.next().getStringCellValue();
			dni = columnas.next().getStringCellValue();
			
			citizen = new Citizen(i++,nombre, apellidos, email, fechaNacimiento, residencia, nacionalidad, dni, nombre, nombre);
			citizens.add(citizen);
		}catch(NoSuchElementException ne){
			System.out.println("Número de campos incorrectos en fila: " + nombre + " " + email);
		}
	}
}
