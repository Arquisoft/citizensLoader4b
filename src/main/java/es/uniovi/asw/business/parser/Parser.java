package es.uniovi.asw.business.parser;

import java.io.File;
import java.util.*;

import es.uniovi.asw.model.Citizen;
import es.uniovi.asw.util.CitizenException;

public interface Parser {
    List<Citizen> loadUsers(File fichero) throws CitizenException;
}
