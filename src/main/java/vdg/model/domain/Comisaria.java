package vdg.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comisaria")
public class Comisaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	int idComisaria;
	@Column
	String nombre;
	@Column
	String ciudad;
	@Column 
	String direccion;
	@Column 
	String telefono;
	@Column
	String partido;
	@Column
	String comisariaACargo;
	@Column 
	@Enumerated(EnumType.STRING)
	TipoComisaria tipo;
	@Column
	String coordenadaX;
	@Column 
	String coordenadaY;
	@Column 
	Long idComisariaTelegram;
	
	
	
	

	/**
	 * Obtiene el id de la comisaria
	 * 
	 * **/
	public int getIdComisaria() {
		return idComisaria;
	}
	
	/**
	 * Setea el id de la comisaria
	 * 
	 * **/
	public void setIdComisaria(int idComisaria) {
		this.idComisaria = idComisaria;
	}
	
	/**
	 * Obtiene el nombre de la comisaria
	 * 
	 * **/
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setea el nombre de la comisaria
	 * 
	 * **/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtiene la ciudad de la comisaria  
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
	 * Obtiene la dirección de la comisaria 
	 * **/
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Setea la dirección de la comisaria 
	 *
	 ***/
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Obtiene el telefono de la comisaria 
	 *
	 ***/
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Setea el telefono de la comisaria 
	 *
	 ***/
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Obtiene el partido de la comisaria 
	 *
	 ***/
	public String getPartido() {
		return partido;
	}
	
	/**
	 * Setea el partido de la comisaria 
	 *
	 ***/
	public void setPartido(String partido) {
		this.partido = partido;
	}
	
	/**
	 * Obtiene el comisarioAcargo 
	 ***/
	public String getComisariaACargo() {
		return comisariaACargo;
	}
	
	/**
	 * Setea el comisarioAcargo 
	 ***/
	public void setComisariaACargo(String comisariaACargo) {
		this.comisariaACargo = comisariaACargo;
	}
	
	/**
	 * Obtiene el tipo 
	 ***/
	public TipoComisaria getTipo() {
		return tipo;
	}
	
	/**
	 * Setea el tipo 
	 ***/
	public void setTipo(TipoComisaria tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Obtiene la coordena x de la comisaria
	 ***/
	public String getCoordenadaX() {
		return coordenadaX;
	}
	
	/**
	 * Setea la coordena x de la comisaria
	 ***/
	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	
	/**
	 * Obtiene la coordena y de la comisaria
	 ***/
	public String getCoordenadaY() {
		return coordenadaY;
	}
	
	/**
	 * Setea la coordena y de la comisaria
	 ***/
	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	public Long getIdComisariaTelegram() {
		return idComisariaTelegram;
	}

	public void setIdComisariaTelegram(Long idComisariaTelegram) {
		this.idComisariaTelegram = idComisariaTelegram;
	}
	
	
	
}
