package es.uniovi.asw.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import es.uniovi.asw.model.Citizen;

/**
 * Clase que verifica los datos de entrada y si falta algún atributo
 * obligatorio genera el correspondiente error.
 * 
 * @author Raúl Gómez Pérez
 *
 */
public class InsertP implements Insert {
	
	@Autowired
	CitizenRepository repository;
	
	@Override
	public List<Citizen> save(List<Citizen> citizens) {
		
		for(Citizen citizen: citizens){
			try{
				if(citizen != null ){
					repository.save(citizen);
				}
			}catch(DataIntegrityViolationException e){
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		return null;
	}

}
