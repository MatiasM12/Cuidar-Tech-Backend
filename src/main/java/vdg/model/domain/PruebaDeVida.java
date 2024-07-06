package vdg.model.domain;

import java.sql.Timestamp;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "PruebaDeVida")
public class PruebaDeVida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idPruebaDeVida;

    @Column
    private Timestamp fecha;
    
    @Column
    private Timestamp tiempoDeRespuesta;

    @Column
    private String descripcion;

    @Column
    @Enumerated(EnumType.STRING)
    private EstadoPruebaDeVida estado;

    @Column
    @Enumerated(EnumType.STRING)
    private AccionPruebaDeVida accion;

    @Column
    private int idRestriccion;

    @Column
    private int idPersonaRestriccion;
    
    
    @Column
    private Boolean esMultiple;
    
    @Column
    private Long idPruebaDeVidaMultiple;


    public PruebaDeVida() {
        // TODO Auto-generated constructor stub
    }

    public int getIdPruebaDeVida() {
        return idPruebaDeVida;
    }

    public void setIdPruebaDeVida(int idPruebaDeVida) {
        this.idPruebaDeVida = idPruebaDeVida;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Timestamp getTiempoDeRespuesta() {
		return tiempoDeRespuesta;
	}

	public void setTiempoDeRespuesta(Timestamp tiempoDeRespuesta) {
		this.tiempoDeRespuesta = tiempoDeRespuesta;
	}

	public Boolean getEsMultiple() {
		return esMultiple;
	}

	public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoPruebaDeVida getEstado() {
        return estado;
    }

    public void setEstado(EstadoPruebaDeVida estado) {
        this.estado = estado;
    }

    public AccionPruebaDeVida getAccion() {
        return accion;
    }

    public void setAccion(AccionPruebaDeVida accion) {
        this.accion = accion;
    }

    public int getIdRestriccion() {
        return idRestriccion;
    }

    public void setIdRestriccion(int idRestriccion) {
        this.idRestriccion = idRestriccion;
    }

    public int getIdPersonaRestriccion() {
        return idPersonaRestriccion;
    }

    public void setIdPersonaRestriccion(int idPersonaRestriccion) {
        this.idPersonaRestriccion = idPersonaRestriccion;
    }

	public Boolean isEsMultiple() {
		return esMultiple;
	}

	public void setEsMultiple(Boolean esMultiple) {
		this.esMultiple = esMultiple;
	}

	public Long getIdPruebaDeVidaMultiple() {
		return idPruebaDeVidaMultiple;
	}

	public void setIdPruebaDeVidaMultiple(Long idPruebaDeVidaMultiple) {
		this.idPruebaDeVidaMultiple = idPruebaDeVidaMultiple;
	}
    

}
