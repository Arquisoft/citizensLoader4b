package es.uniovi.asw.dbupdate.util;

import java.util.List;

import es.uniovi.asw.model.Citizen;

public class CitizensFinder {

	public static List<Citizen> findByDNI(String dni) {
		return Jpa.getManager()
				.createNamedQuery("User.findByDni", Citizen.class)
				.setParameter(1, dni).getResultList();
	}

	public static List<Citizen> findByEmail(String email) {
		return Jpa.getManager()
				.createNamedQuery("User.findByEmail", Citizen.class)
				.setParameter(1, email).getResultList();
	}

}
