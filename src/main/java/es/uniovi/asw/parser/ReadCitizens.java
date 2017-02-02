package es.uniovi.asw.parser;

import java.io.IOException;
import java.util.List;

import es.uniovi.asw.model.Citizen;

public interface ReadCitizens {
	List<Citizen> readCitizens(String...args) throws IOException;
}
