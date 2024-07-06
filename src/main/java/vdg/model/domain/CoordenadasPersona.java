package vdg.model.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class CoordenadasPersona {
	@Column
	String dia;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	public int idDato;
	@Column
	public BigDecimal latitud;
	@Column
	public BigDecimal longitud;
	@Column
	public Timestamp fecha;
	@ManyToOne
	@JoinColumn(name = "idPersona")
	public Persona idPersona;
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public int getIdDato() {
		return idDato;
	}
	public void setIdDato(int idDato) {
		this.idDato = idDato;
	}
	public BigDecimal getLatitud() {
		return latitud;
	}
	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}
	public BigDecimal getLongitud() {
		return longitud;
	}
	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public Persona getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Persona idPersona) {
		this.idPersona = idPersona;
	}
	
	
	
	
	
}
