package vdg.model.notificacionesTerceros;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springfox.documentation.spring.web.json.Json;



public interface Notificador {

	
	public String notificar(CuerpoNotificacion cuerpo);
}
