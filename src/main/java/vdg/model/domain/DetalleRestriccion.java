package vdg.model.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DetalleRestriccion")
public class DetalleRestriccion {

    @Id
    @Column(name = "idDetalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;

    @OneToOne
    @JoinColumn(name = "idRestriccion")
    private RestriccionPerimetral restriccion;
    
    @Column
    private String juez;
    
     @Column
     private String detalle;
     
     @OneToOne
     @JoinColumn(name = "idComisaria")
     private Comisaria comisaria;

    @OneToOne
    @JoinColumn(name = "idJuzgado")
    private Juzgado juzgado;


    public DetalleRestriccion() {
    }


	public int getIdDetalle() {
		return idDetalle;
	}


	@Override
	public String toString() {
		return "DetalleRestriccion [idDetalle=" + idDetalle + ", restriccion=" + restriccion + ", juez=" + juez
				+ ", detalle=" + detalle + ", comisaria=" + comisaria + ", juzgado=" + juzgado + "]";
	}


	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}


	public RestriccionPerimetral getRestriccion() {
		return restriccion;
	}


	public void setRestriccion(RestriccionPerimetral restriccion) {
		this.restriccion = restriccion;
	}





	public String getJuez() {
		return juez;
	}


	public void setJuez(String juez) {
		this.juez = juez;
	}


	public String getDetalle() {
		return detalle;
	}


	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	public Juzgado getJuzgado() {
		return juzgado;
	}


	public void setJuzgado(Juzgado juzgado) {
		this.juzgado = juzgado;
	}


	public Comisaria getComisaria() {
		return comisaria;
	}


	public void setComisaria(Comisaria comisaria) {
		this.comisaria = comisaria;
	}
    
}

