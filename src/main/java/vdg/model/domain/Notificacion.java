package vdg.model.domain;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "Notificacion")
public class Notificacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idNotificacion;
	
	@Column
	private Timestamp fecha;
	
	@Column
	private String asunto;
	
	@Column
	private String descripcion;
	
	@Column
	@Enumerated(EnumType.STRING)
	private EstadoNotificacion estadoNotificacion;
	
	@Column
	private int idUsuario;

	public Notificacion() {}

	public int getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(int idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoNotificacion getEstado() {
		return estadoNotificacion;
	}

	public void setEstado(EstadoNotificacion estado) {
		this.estadoNotificacion = estado;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
		
}
