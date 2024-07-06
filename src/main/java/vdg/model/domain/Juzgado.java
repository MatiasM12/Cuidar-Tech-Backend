package vdg.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "juzgado")
public class Juzgado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	int idJuzgado;
	@Column
	String nombre;
	@Column
	String ciudad;
	@Column 
	String direccion;
	@Column 
	String contacto;
	@Column
	String jurisdiccion;
	@Column
	String coordenadaX;
	@Column 
	String coordenadaY;
	@Column 
	Long idJuzgadoTelegram;
	
	
	/**
	 * Obtiene el id
	 * 
	 * **/
	public int getIdJuzgado() {
		return idJuzgado;
	}
	
	/**
	 * Setea el id 
	 * 
	 * **/
	public void setIdJuzgado(int idJuzgado) {
		this.idJuzgado = idJuzgado;
	}
	
	/**
	 * Obtiene el nombre 
	 * 
	 * **/
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setea el nombre 
	 * 
	 * **/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene la ciudad  
	 * **/
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * Setea la ciudad de la comisaria 
	 *
	 ***/
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	/**
	 * Obtiene la dirección 
	 * **/
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Setea la dirección 
	 *
	 ***/
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Obtiene el contacto
	 *
	 ***/
	public String getContacto() {
		return contacto;
	}
	
	/**
	 * Setea el contacto
	 *
	 ***/
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
	/**
	 * Obtiene la jurisdiccion
	 *
	 ***/
	public String getJurisdiccion() {
		return jurisdiccion;
	}
	
	/**
	 * Setea la jurisdiccion
	 *
	 ***/
	public void setJurisdiccion(String jurisdiccion) {
		this.jurisdiccion= jurisdiccion;
	}
	
	/**
	 * Obtiene la coordenada x del juzgado
	 ***/
	public String getCoordenadaX() {
		return coordenadaX;
	}
	
	
	/**
	 * Setea la coordenada x del juzgado
	 ***/
	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	
	/**
	 * Obtiene la coordenada y del juzgado
	 ***/
	public String getCoordenadaY() {
		return coordenadaY;
	}
	
	/**
	 * Setea la coordenada y del juzgado
	 ***/
	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	
	

	public Long getIdJuzgadoTelegram() {
		return idJuzgadoTelegram;
	}

	public void setIdJuzgadoTelegram(Long idJuzgadoTelegram) {
		this.idJuzgadoTelegram = idJuzgadoTelegram;
	}
	
	
	
	
	
	
}
