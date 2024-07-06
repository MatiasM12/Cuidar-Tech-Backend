package vdg.model.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Incidencia")
public class Incidencia {
	
	@Id
	@Column
	private int idIncidencia;
	
	@Column
	private Timestamp fecha;
	
	@Column
	private String descripcion;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TipoIncidencia topico;
	
	@Column
	private int idRestriccion;
	
	
	@Enumerated(EnumType.STRING)
	private Peligrosidad peligrosidad;
	
	public Incidencia() {
		
	}

	public int getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(int idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoIncidencia getTopico() {
		return topico;
	}

	public void setTopico(TipoIncidencia topico) {
		this.topico = topico;
	}

	public int getIdRestriccion() {
		return idRestriccion;
	}

	public void setIdRestriccion(int idRestriccion) {
		this.idRestriccion = idRestriccion;
	}

	public Peligrosidad getPeligrosidad() {
		return peligrosidad;
	}

	public void setPeligrosidad(Peligrosidad peligrosidad) {
		this.peligrosidad = peligrosidad;
	}

	
	

	
}
