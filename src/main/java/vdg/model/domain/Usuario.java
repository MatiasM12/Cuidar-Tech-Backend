package vdg.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idUsuario;
	
	@Column
	private int idGrupo;

	@Column
	private String email;

	@Column
	private String contrasena;
	
	@Column
	@Enumerated(EnumType.STRING)
	private RolDeUsuario rolDeUsuario;
	
	@Column
	@Enumerated(EnumType.STRING)
	private EstadoUsuario estadoUsuario;
	
	
	public Usuario() {
		
	}
	
	
	
	
	public int getIdGrupo() {
		return idGrupo;
	}




	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}




	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasena() {
		return contrasena;
	}
	
	
	
	
	
	
	
	/**
	 * setea la contraseña
	 * @return void
	 * **/
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	/**
	 * Obtiene el rol del usuario
	 * @return RolDeUsuario
	 * **/
	public RolDeUsuario getRolDeUsuario() {
		return rolDeUsuario;
	}
	
	/**
	 * Obtiene el estado del usuario**/
	public EstadoUsuario getEstadoUsuario() {
		return this.estadoUsuario;
	}
	
	/**
	 * Setea el rol del usuario
	 * @return void
	 * **/
	public void setRolDeUsuario(RolDeUsuario rolDeUsuario) {
		this.rolDeUsuario = rolDeUsuario;
	}
	/**
	 * Setea el estado del usuario
	 * @return void
	 * **/
	public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}
	
}
