package es.uniovi.asw.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import es.uniovi.asw.model.Citizen;

/**
 * Clase que verifica los datos de entrada y si falta algún atributo obligatorio
 * genera el correspondiente error.
 * 
 * @author Raúl Gómez Pérez
 *
 */
public class InsertP implements Insert {

	@Autowired
	private CitizenRepository repository;

	@Override
	public List<Citizen> save(List<Citizen> citizens) {
<<<<<<< HEAD:src/main/java/es/uniovi/asw/DBUpdate/InsertP.java
		
		List<Citizen> addedCitizens = new ArrayList<Citizen>();
		
		for(Citizen citizen: citizens){
			try{
				if(citizen != null ){
=======

		for (Citizen citizen : citizens) {
			try {
				if (citizen != null) {
>>>>>>> a1a085fb08f986870e6fe378b81996460699a569:src/main/java/es/uniovi/asw/persistence/InsertP.java
					repository.save(citizen);
					addedCitizens.add(citizen);
				}
<<<<<<< HEAD:src/main/java/es/uniovi/asw/DBUpdate/InsertP.java
			}catch(DataIntegrityViolationException e){
				//Falta reportar cuando un ciudadano no se puede insertar
				e.printStackTrace();
			}catch(Exception e2){
				//Error con la base de datos
				e2.printStackTrace();
			}
		}
		
		return addedCitizens;
=======
			} catch (DataIntegrityViolationException e) {

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return null;
>>>>>>> a1a085fb08f986870e6fe378b81996460699a569:src/main/java/es/uniovi/asw/persistence/InsertP.java
	}

}
