package vdg.model.dto;

import java.math.BigDecimal;

import vdg.model.domain.Direccion;
import vdg.model.domain.Persona;
import vdg.model.domain.Usuario;

public class FormPersonaDTO {

	private Persona persona;
	private Usuario usuario;
	private Direccion direccion;
	private String foto;
	private BigDecimal lat;
	private BigDecimal lon;
	
	
	public Persona getPersona() {
		return this.persona;
	}
	
	public Usuario getUsuario( ) {
		return this.usuario;
	}

	public Direccion getDireccion() {
		return this.direccion;
	}
	
	public String getFoto() {
		return this.foto;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}
	
	
	
}
