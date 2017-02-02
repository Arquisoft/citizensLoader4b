package es.uniovi.asw.parser;

import java.util.*;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.model.Citizen;

public interface Parser {
	List<Citizen> loadUsers(String fichero) throws CitizenException;
}
