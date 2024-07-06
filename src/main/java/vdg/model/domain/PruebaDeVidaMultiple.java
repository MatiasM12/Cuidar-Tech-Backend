package vdg.model.domain;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "PruebaDeVidaMultiple")
public class PruebaDeVidaMultiple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idPruebaDeVidaMultiple;

    @Column
    private String descripcion;
    
    @Column
    private Long idPersona;
    
    @Column 
    @Enumerated(EnumType.STRING)
    private EstadoPruebaDeVida estado; 

    @Column
    private Timestamp tiempoDeRespuesta;

    public PruebaDeVidaMultiple() {
        super();
    }

    public Long getIdPruebaDeVidaMultiple() {
        return idPruebaDeVidaMultiple;
    }

    public void setIdPruebaDeVidaMultiple(Long id) {
        this.idPruebaDeVidaMultiple = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public EstadoPruebaDeVida getEstado() {
		return estado;
	}

	public void setEstado(EstadoPruebaDeVida estado) {
		this.estado = estado;
	}
	
    public Timestamp getTiempoDeRespuesta() {
		return tiempoDeRespuesta;
	}

	public void setTiempoDeRespuesta(Timestamp tiempoDeRespuesta) {
		this.tiempoDeRespuesta = tiempoDeRespuesta;
	}

	
}
