package es.uniovi.asw.parser;

import java.util.List;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public interface ReadCitizens {
	List<Citizen> readCitizens(String... args) throws CitizenException;
}
