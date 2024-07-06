
package vdg.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RestriccionMultiple")
public class RestriccionMultiple {
    @Id
    @Column(name = "idRestriccionMultiple")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRestriccionMultiple;
    @Column
    private int idRestriccion;
    @Column
    private int idPersona; 
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDireccion")  
    private Direccion direccion;
    @Column
    private int distancia;
    @Column 
    private int idProvincia;
    /**
     * Obtiene el id de la restricción multiple
     * @author Nicolas
     * */
    public int getidRestriccionMultiple() {
        return idRestriccionMultiple;
    }

    /**
     * Obtiene el id de la restricción a la que esta asociada la restriccion multiple
     * @author Nicolas
     * */
    public int getIdRestriccion() {
        return idRestriccion;
    }
    
    
    /**
     * Setea el id de la restricción a la que esta asociada la restricción multiple
     * @author Nicolas
     * */
    public void setIdRestriccion(int idRestriccion) {
        this.idRestriccion = idRestriccion;
    }

    
    
    /**
     * Obtiene la dirección de la restricción a la que esta asociada la restricción multiple
     * @author Nicolas
     * */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * setea el id de la restricción a la que esta asociada la restricción multiple
     * @author Nicolas
     * */
     public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
     }

 
    /**
     * Obtiene el la distancia de la restricción multiple
     * @author Nicolas
     * */
    public int getDistancia() {
        return distancia;
    }

    /**
     * Setea el la distancia de la restricción multiple
     * @author Nicolas
     * */
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }


  
    /**
     * Obtiene el id de la persona de la restricción multiple
     * @author Nicolas
     * */
	public int getIdPersona() {
		return idPersona;
	}

	
	/**
     * Setea el id de la persona de la restricción multiple
     * @author Nicolas
     * */
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	/**
     * obtiene el id de la provincia asociada a la  restricción multiple
     * @author Nicolas
     * */
	public int getIdProvincia() {
		return idProvincia;
	}

	/**
     * Setea el id de la provincia asociada a la  restricción multiple
     * @author Nicolas
     * */
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
    
    
}