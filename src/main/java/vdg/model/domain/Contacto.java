package vdg.model.domain;

import javax.persistence.*;

@Entity
@Table(name="Contacto")
public class Contacto {
	
	@Id
	@Column
	private int idContacto;
	
	@Column
	private String apellido;
	
	@Column
	private String nombre;
	
	@Column
	private String email;
	
	@Column
	private String telefono;
	
	@Column
	private String relacion;
	
	@Column
	private int idDamnificada;
	
	public Contacto() {
		// TODO Auto-generated constructor stub
	}

	public int getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRelacion() {
		return relacion;
	}

	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}

	public int getIdDamnificada() {
		return idDamnificada;
	}

	public void setIdDamnificada(int idDamnificada) {
		this.idDamnificada = idDamnificada;
	}

}
