package vdg.model.notificacionesTerceros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.spring.web.json.Json;





@Service
public class WpNotificador implements Notificador{

	@Value("${url.notificador.wp}")
	private  String url;
	
	@Value("${version.notificador.wp}")
	private  String version;
	
	@Value("${nroTelefono}")
	private  String telefono;
	
	@Value("${tokenwp}")
	private  String token;
	
	
	private final RestTemplateBuilder templateBuilder;
	private final HttpHeaders headers;
	
	@Autowired
	public WpNotificador(RestTemplateBuilder templateBuilder,HttpHeaders headers){
		this.templateBuilder = templateBuilder;
		this.headers = headers;
	}
	
	@Override
	public String notificar(CuerpoNotificacion cuerpo) {
		this.headers.setContentType(MediaType.APPLICATION_JSON);
		this.headers.set("Authorization", "Bearer "+this.token);
		HttpEntity<CuerpoNotificacion> entity = new HttpEntity<CuerpoNotificacion>(cuerpo,headers);
		
		
		return this.templateBuilder
		.build()
		.postForObject(this.url+this.version+"/"+this.telefono+"/messages?", entity, String.class);
		
		
		
		
	}
	
	
	
	

	
	
	
}
