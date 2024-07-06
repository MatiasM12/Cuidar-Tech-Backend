package vdg.model.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;

import vdg.model.domain.UbicacionNormalizada;

public class NormalizacionCoordenadas {
	
	
	
	@Value("${API_GEOLOCALIZACION}")
	String urlApi;
	
	
	
	private final RestTemplateBuilder templateBuilder;
	
	
	@Autowired
	public NormalizacionCoordenadas(RestTemplateBuilder templateBuilder){
		this.templateBuilder = templateBuilder;
	}
	
	
	
	public UbicacionNormalizada ObtenerCoordenadas(double lat, double lon){		
		return this.templateBuilder.build().getForObject(this.urlApi+"lat="+lat+"&lon="+lon, UbicacionNormalizada.class);
		
	}
	
}
