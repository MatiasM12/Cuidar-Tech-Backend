package vdg.model.domain;

public class RestriccionMultipleDTO {

	
	
	
	RestriccionMultiple restriccionMultiple;
	Provincia provincia;
	Localidad localidad;
	Persona persona;
	
	public RestriccionMultipleDTO(RestriccionMultiple restriccionMultiple,Provincia provincia
			,Localidad localidad,Persona persona) {
		this.restriccionMultiple = restriccionMultiple;
		this.provincia = provincia;
		this.localidad = localidad;
		this.persona = persona;
	}

	public RestriccionMultiple getRestriccionMultiple() {
		return restriccionMultiple;
	}

	public void setRestriccionMultiple(RestriccionMultiple restriccionMultiple) {
		this.restriccionMultiple = restriccionMultiple;
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	

	
	
	
	
	
}
