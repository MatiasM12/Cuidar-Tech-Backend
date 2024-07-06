package vdg.model.domain;

public class RPLugarDTO {

	private RPLugar rpLugar;
	private Provincia provincia;
	private Localidad localidad;
	
	
	public RPLugarDTO(RPLugar rpLugar, Provincia provincia, Localidad localidad) {
		super();
		this.rpLugar = rpLugar;
		this.provincia = provincia;
		this.localidad = localidad;
	}

	public RPLugarDTO() {
		super();
	}

	public RPLugar getRpLugar() {
		return rpLugar;
	}
	
	public void setRpLugar(RPLugar rpLugar) {
		this.rpLugar = rpLugar;
	}
	
	public Provincia getProvincia() {
		return provincia;
	}
	
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public Localidad getLocalidad() {
		return localidad;
	}
	
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	
}
