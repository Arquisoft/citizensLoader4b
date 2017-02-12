package es.uniovi.asw.model;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import es.uniovi.asw.common.CitizenException;
import es.uniovi.asw.util.EncryptMD5;

@Entity
@Table(name = "TCITIZENS")
public class Citizen {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	private String nombre;
	@NotNull
	private String apellidos;
	@NotNull
	private String email;
	@NotNull
	private Date fechaNacimiento;
	private String residencia;
	private String nacionalidad;
	@NotNull
	private String dni;
	@NotNull
	private String password;

	private Calendar calendar = GregorianCalendar.getInstance();

	public Citizen() {
	}

	public Citizen(long id, String nombre, String apellidos, String email,
			Date fechaNacimiento, String residencia, String nacionalidad,
			String dni) throws NoSuchAlgorithmException, CitizenException {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.residencia = residencia;
		this.nacionalidad = nacionalidad;
		this.dni = dni;
		this.password = generarPassword();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) throws CitizenException {
		if (id > 0)
			this.id = id;
		else
			throw new CitizenException("El ID es menor que 0");
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
		this.password = EncryptMD5.encrypting(password);
	}

	private String generarPassword()
			throws NoSuchAlgorithmException, CitizenException {
		String password = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;

		while (i < 10) {
			char c = (char) r.nextInt(255);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')
					|| (c >= 'a' && c <= 'z')) {
				password += c;
				i++;
			}
		}

		return EncryptMD5.encrypting(password);
	}

	@Override
	public String toString() {
		return "Citizen [id=" + id + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", email=" + email + ", fechaNacimiento="
				+ fechaNacimiento + ", residencia=" + residencia
				+ ", nacionalidad=" + nacionalidad + ", dni=" + dni
				+ ", password=" + password + "]";
	}

}
