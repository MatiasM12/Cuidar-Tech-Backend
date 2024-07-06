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
@Table(name = "dependenciaGenero")
public class DependenciaGenero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	int idDependencia;
	@Column
	String nombre;
	@Column
	String ciudad;
	@Column 
	String direccion;
	@Column 
	String telefono;
	@Column
	String municipio;
	@Column
	String provincia;
	@Column 
	Long idComisariaTelegram;
	
	
	public int getIdDependencia() {
		return idDependencia;
	}
	public void setIdDependencia(int idDependencia) {
		this.idDependencia = idDependencia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public Long getIdComisariaTelegram() {
		return idComisariaTelegram;
	}
	public void setIdComisariaTelegram(Long idComisariaTelegram) {
		this.idComisariaTelegram = idComisariaTelegram;
	}
	
	
	


}
	
	
	