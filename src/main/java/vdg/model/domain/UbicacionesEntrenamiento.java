package vdg.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;

@Entity
public class UbicacionesEntrenamiento implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public UbicacionesEntrenamiento() {
		
	}
	
	public UbicacionesEntrenamiento(String dia, int idDato, @Digits(integer = 4, fraction = 10) BigDecimal latitud,
			@Digits(integer = 4, fraction = 10) BigDecimal longitud, Timestamp fecha, Persona idPersona) {
		super();
		this.dia = dia;
		this.idDato = idDato;
		this.latitud = latitud;
		this.longitud = longitud;
		this.fecha = fecha;
		this.idPersona = idPersona;
	}
	
	
	
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
	@Override
	public String toString() {
		return "UbicacionesEntrenamiento [dia=" + dia + ", idDato=" + idDato + ", latitud=" + latitud + ", longitud="
				+ longitud + ", fecha=" + fecha + ", idPersona=" + idPersona + "]";
	}

	
	

	

}
