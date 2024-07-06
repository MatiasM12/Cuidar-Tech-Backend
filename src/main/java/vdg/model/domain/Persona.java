package vdg.model.domain;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "Persona")
public class Persona {

	@Id
	@Column
	private int idPersona;

	@Column
	private String nombre;

	@Column
	private String apellido;

	@Column
	private String dni;

	@Column
	private String telefono;

	@Column
	private Date fechaNacimiento;

	@Column
	private int idDireccion;

	@Column
	private int idUsuario;

	public Persona() {

	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDNI() {
		return dni;
	}

	public void setDNI(String dNI) {
		dni = dNI;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String nombreToString() {
		return this.apellido+", "+this.nombre;
	}
}
