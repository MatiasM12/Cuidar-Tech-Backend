package vdg.model.controladorRutina;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "UbicacionRutina")
public class UbicacionRutina {

	@Id
	@Column
	private int idUbicacionRutina;
	
	@Column
	private BigDecimal latitud;
	
	@Column
	private BigDecimal longitud;
	
	@Column
	private Timestamp fecha;
	
	@Column
	private int idPersona;
	
	public UbicacionRutina() {
	}

	public int getIdUbicacionRutina() {
		return idUbicacionRutina;
	}

	public void setIdUbicacionRutina(int idUbicacionRutina) {
		this.idUbicacionRutina = idUbicacionRutina;
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
	
}
