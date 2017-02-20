package es.uniovi.asw.dbupdate;

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
		List<Citizen> addedCitizens = new ArrayList<Citizen>();
		for (Citizen citizen : citizens) {
			try {
				if (citizen != null) {
					repository.save(citizen);
					addedCitizens.add(citizen);
				}
			} catch (DataIntegrityViolationException e) {
				// Falta reportar cuando un ciudadano no se puede insertar
				e.printStackTrace();
			} catch (Exception e2) {
				// Error con la base de datos
				e2.printStackTrace();
			}
		}
		return addedCitizens;
	}

}
