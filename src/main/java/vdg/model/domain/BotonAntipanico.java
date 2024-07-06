package vdg.model.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name="BotonAntipanico")
public class BotonAntipanico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
	@Column(name = "idBotonAntipanico")
	private int idBotonAntipanico;
	
	@Column
	private BigDecimal latitud;

	@Column
	private BigDecimal longitud;

	@Column
	private Timestamp fecha;

	@Column
	private int idDamnificada;
	
	public BotonAntipanico() {
		// TODO Auto-generated constructor stub
	}

	public int getIdBotonAntipanico() {
		return idBotonAntipanico;
	}

	public void setIdBotonAntipanico(int idBotonAntipanico) {
		this.idBotonAntipanico = idBotonAntipanico;
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

	public int getIdDamnificada() {
		return idDamnificada;
	}

	public void setIdDamnificada(int idDamnificada) {
		this.idDamnificada = idDamnificada;
	}

}
