package vdg.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RPLugar")
public class RPLugar {

    @Id
    @Column(name = "idRPLugar")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRPLugar;
    @Column
    private int idRestriccion;
    @Column
    private String nombre;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDireccion")  
    private Direccion direccion;
    @Column
    private Double longuitud;
    @Column
    private Double latitud;
    @Column
    private int distancia;


    public RPLugar() {
    }

    // Getters y Setters

    public int getidRPLugar() {
        return idRPLugar;
    }

    public void setidRPLugar(int idRPLugar) {
        this.idRPLugar = idRPLugar;
    }
    
    public int getIdRestriccion() {
        return idRestriccion;
    }

    public void setIdRestriccion(int idRestriccion) {
        this.idRestriccion = idRestriccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Double getLonguitud() {
        return longuitud;
    }

    public void setLonguitud(Double longuitud) {
        this.longuitud = longuitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
}

