package vdg.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "Localidad")
public class Localidad {
	
	@Id
	@Column
	private int idLocalidad;

	@Column
	private String nombre;
	
	@Column
	private int codigoPostal;
	
	@Column
	private int idProvincia;

	public Localidad() {
		
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	
}
