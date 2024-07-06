package vdg.model.domain;


import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name="Infraccion")
public class Infraccion {
	
	@Id
	@Column
	private int idInfraccion;

	@Column
	private Timestamp fecha;

	@Column
	private int distancia;

	@Column
	@Enumerated(EnumType.STRING)
	private EstadoInfraccion estadoInfraccion;

	@Column
	private int idRestriccion;
	
	public Infraccion() {
		
	}

	public int getIdInfraccion() {
		return idInfraccion;
	}

	public void setIdInfraccion(int idInfraccion) {
		this.idInfraccion = idInfraccion;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getIdRestriccion() {
		return idRestriccion;
	}

	public void setIdRestriccion(int idRestriccion) {
		this.idRestriccion = idRestriccion;
	}

	public EstadoInfraccion getEstadoInfraccion() {
		return estadoInfraccion;
	}

	public void setEstadoInfraccion(EstadoInfraccion estadoInfraccion) {
		this.estadoInfraccion = estadoInfraccion;
	}

}
