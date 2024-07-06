package vdg.model.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "Ubicacion")
public class Ubicacion {

	@Id
	@Column
	private int idUbicacion;
	
	@Column
	private BigDecimal latitud;
	
	@Column
	private BigDecimal longitud;
	
	@Column
	private Timestamp fecha;
	
	@Column
	private int idPersona;
	
	public Ubicacion() {
	}

	public int getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
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

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	@Override
	public String toString() {
		return "[idUbicacion=" + idUbicacion + ", latitud=" + latitud + ", longitud=" + longitud + ", fecha="
				+ fecha + ", idPersona=" + idPersona + "]";
	}
	
	
	
}
