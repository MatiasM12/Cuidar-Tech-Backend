package vdg.model.domain;

public class UbicacionFisica {

	public Departamento departamento;
	public double lat;
	public double lon;
	public Municipio municipio;
	public ProvinciaFisica provincia;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public ProvinciaFisica getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaFisica provincia) {
		this.provincia = provincia;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
}
