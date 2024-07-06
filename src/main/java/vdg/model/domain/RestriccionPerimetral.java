package vdg.model.domain;

import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "RestriccionPerimetral")
public class RestriccionPerimetral {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idRestriccion;
	@Column
	private int idUsuario;
	@Column
	private int idDamnificada;
	
	@Column
	private int idVictimario;
	
	@Column
	private int distancia;
	
	@Column
	private Date fechaSentencia;
	
	@Column
	private Integer idGrupo; 
	
	@Column
	private Integer idDependenciaGenero;
	
	
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public RestriccionPerimetral() {
		
	}
	public int getIdRestriccion() {
		return idRestriccion;
	}

	public void setIdRestriccion(int idRestriccion) {
		this.idRestriccion = idRestriccion;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdDamnificada() {
		return idDamnificada;
	}

	public void setIdDamnificada(int idDamnificada) {
		this.idDamnificada = idDamnificada;
	}

	public int getIdVictimario() {
		return idVictimario;
	}

	public void setIdVictimario(int idVictimario) {
		this.idVictimario = idVictimario;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Date getFechaSentencia() {
		return fechaSentencia;
	}

	public void setFechaSentencia(Date fechaSentencia) {
		this.fechaSentencia = fechaSentencia;
	}
	public Integer getIdDependenciaGenero() {
		return idDependenciaGenero;
	}
	public void setIdDependenciaGenero(Integer idDependenciaGenero) {
		this.idDependenciaGenero = idDependenciaGenero;
	}
	@Override
	public String toString() {
		return "RestriccionPerimetral [idRestriccion=" + idRestriccion + ", idUsuario=" + idUsuario + ", idDamnificada="
				+ idDamnificada + ", idVictimario=" + idVictimario + ", distancia=" + distancia + ", fechaSentencia="
				+ fechaSentencia + ", idGrupo=" + idGrupo + "]";
	}
	
	
	
}
