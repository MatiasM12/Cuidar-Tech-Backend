package vdg.model.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "grupo")
/**Clase que representa a la entidad grupo**/
public class Grupo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer idGrupo;
	@Column
	@Enumerated(EnumType.STRING)
	private TurnoGrupo turnoGrupo;
	@Column
    private String nombreGrupo;
	
	

	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public TurnoGrupo getTurnoGrupo() {
		return turnoGrupo;
	}
	public void setTurnoGrupo(TurnoGrupo turnoGrupo) {
		this.turnoGrupo = turnoGrupo;
	}
	public String getNombreGrupo() {
		return nombreGrupo;
	}
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}
	
	
}