package es.uniovi.asw.parser;

import java.io.File;
import java.util.List;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public interface ReadCitizens {
	List<Citizen> readCitizens(File fichero) throws CitizenException;
}
