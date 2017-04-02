package es.uniovi.asw.model;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import es.uniovi.asw.util.CitizenException;
import es.uniovi.asw.util.EncryptMD5;

@Entity
@Table(name = "TCITIZENS")
public class Citizen {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String nombre;
	@NotNull
	private String apellidos;
	@NotNull
	private String email;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@NotNull
	private String residencia;

	@NotNull
	private String nacionalidad;

	@NotNull
	private String dni;

	@NotNull
	private String password;

	private boolean isAdmin;

	public Citizen() {
	}

	public Citizen(String nombre, String apellidos, String email,
			Date fechaNacimiento, String residencia, String nacionalidad,
			String dni) throws NoSuchAlgorithmException, CitizenException {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		setFechaNacimiento(fechaNacimiento);
		this.residencia = residencia;
		this.nacionalidad = nacionalidad;
		this.dni = dni;
		this.password = generarPassword();
		if (nombre.equals("admin"))
			this.isAdmin = true;
		else
			this.isAdmin = false;
	}

	public Citizen(long id2, String nombre2, String apellidos2, String email2,
			Date fechaNacimiento2, String residencia2, String nacionalidad2,
			String dni2) throws NoSuchAlgorithmException, CitizenException {
		this(nombre2, apellidos2, email2, fechaNacimiento2, residencia2,
				nacionalidad2, dni2);
		this.id = id2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento)
			throws CitizenException {
		Calendar calendar = GregorianCalendar.getInstance();
		try {
			if (fechaNacimiento.before(calendar.getTime()))
				this.fechaNacimiento = fechaNacimiento;
			else
				throw new CitizenException(
						"La fecha de nacimiento es posterior al dia actual.");
		} catch (NullPointerException e) {
			throw new CitizenException(
					"La fecha de nacimiento no puede ser null.");
		}
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password)
			throws NoSuchAlgorithmException, CitizenException {
		this.password = new EncryptMD5().encrypting(password);
	}

	private String generarPassword()
			throws NoSuchAlgorithmException, CitizenException {
		String password = "";
		Calendar calendar = GregorianCalendar.getInstance();
		Random r = new Random(calendar.getTimeInMillis());
		int i = 0;

		while (i < 10) {
			char c = (char) r.nextInt(255);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')
					|| (c >= 'a' && c <= 'z')) {
				password += c;
				i++;
			}
		}

		return new EncryptMD5().encrypting(password);
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Citizen other = (Citizen) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
