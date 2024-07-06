package vdg.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "FotoIdentificacion")
public class FotoIdentificacion {
	
	@Id
	@Column
	private int idFoto;

	@Column
	@Lob
	private byte[] foto;

	@Column
	private int idPersona;

	public FotoIdentificacion() {
		
	}

	public int getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}	
	
}
