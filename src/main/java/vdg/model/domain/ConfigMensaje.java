package vdg.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConfigMensaje")
public class ConfigMensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMensaje;

    @Column
    private String mensajeAft;
    
    @Column
    private String mensajeBef;

    @Column
    private String tipo;

    private String asunto;

    // Getters y Setters

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

	public String getMensajeAft() {
		return mensajeAft;
	}

	public void setMensajeAft(String mensajeAft) {
		this.mensajeAft = mensajeAft;
	}

	public String getMensajeBef() {
		return mensajeBef;
	}

	public void setMensajeBef(String mensajeBef) {
		this.mensajeBef = mensajeBef;
	}
    
}