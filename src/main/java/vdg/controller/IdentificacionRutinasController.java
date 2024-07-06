package vdg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.domain.ConfiguracionEntrenamiento;
import vdg.model.domain.ConfiguracionLSTM;
import vdg.model.domain.CoordenadasPersona;
import vdg.model.domain.Persona;
import vdg.model.domain.RestriccionMultiple;
import vdg.model.domain.Ubicacion;
import vdg.model.domain.UbicacionesEntrenamiento;
import vdg.model.notificacionesTerceros.TelegramNotificador;
import vdg.model.rutinas.IniciarScript;
import vdg.model.rutinas.SchedulerRutina;
import vdg.repository.ConfiguracionRepository;
import vdg.repository.CoordenadasPersonaRepository;
import vdg.repository.PersonaRepository;
import vdg.repository.UbicacionEntrenamientoRepository;
import vdg.repository.UbicacionRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;



@RestController
@RequestMapping("/IdentificacionRutinas")
@CrossOrigin
public class IdentificacionRutinasController {
	
	
	  @Autowired
	  IniciarScript iniciadorScript;
	  
	  @Autowired
	  UbicacionRepository ubicacion;
	  
	  @Autowired
	  UbicacionEntrenamientoRepository ubicacionEntrenamientoRepository;
	  
	  @Autowired
	  ConfiguracionRepository configRepository;
	  
	  @Autowired
	  CoordenadasPersonaRepository coordenadaPersonaRepository;
	  
	  SchedulerRutina scheduler;
	  
	  
	  
	  
	  @Autowired
	   private  TelegramNotificador telegramNotificador;

	   @PostMapping("crearIdentificador")
	    public ResponseEntity<Void> crearIdentificadorRutinas(@RequestBody ConfiguracionEntrenamiento config) throws Exception {
		  	
		    System.out.println(config.getConfig());
		    this.ubicacionEntrenamientoRepository.deleteByidPersona(config.getConfig().getIdPersona());
			config.getUbicaciones().stream().forEach(ubicacionEntrenamiento ->{
				this.ubicacionEntrenamientoRepository.save(ubicacionEntrenamiento);
			});
			
			this.configRepository.save(config.getConfig());
			
			Persona idPersona = config.getConfig().getIdPersona();
			
			System.out.println(config.getConfig());
		   
		    this.iniciadorScript.crearProceso(config.getConfig());
		    this.iniciadorScript.iniciarProceso();
		
		    
	     
		  	return new ResponseEntity<>(HttpStatus.CREATED);
	       
	    }
	    public ResponseEntity<Void> identificar(Persona idPersona,TelegramNotificador telegram) throws Exception {
	    	List<CoordenadasPersona> coordenadasPersona = this.coordenadaPersonaRepository.findAllByidPersona(idPersona);
	    	System.out.println("Coordenadas ingresadas---------------------------------------------------------------------------------------");
	    	System.out.println(coordenadasPersona);
	    	System.out.println(coordenadasPersona.size());
	    	this.iniciadorScript.predecir(coordenadasPersona,telegram);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	    
	    
	   @PostMapping("entrenar")
	    public ResponseEntity<Void> entrenar(@RequestBody ConfiguracionEntrenamiento config) throws Exception {
		   	List<UbicacionesEntrenamiento> ubicaciones = this.ubicacionEntrenamientoRepository.findAllByIdPersona(config.getConfig().getIdPersona());
		    this.iniciadorScript.entrenar(ubicaciones);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	   
	   
	   
	   @PostMapping("iniciarIdentificacion")
	    public ResponseEntity<Void> iniciar(@RequestBody Persona idPersona) throws Exception {
		   	if(this.scheduler == null) {
				    Timer time = new Timer();
				    SchedulerRutina scheduler = new SchedulerRutina();
				    scheduler.setIdentificacion(this);
				    scheduler.setTelegramNotificador(telegramNotificador);
				    scheduler.setIdPersona(idPersona);
				    time.schedule(scheduler, 0, 15000); 
		    }
		  	
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
	  
	   
	   
	   @GetMapping("obtenerConfiguraciones")
	    public ResponseEntity<List<ConfiguracionLSTM>> obtenerConfiguraciones() throws Exception {
		   	List<ConfiguracionLSTM> configuraciones = this.configRepository.findAll();
		   	return new ResponseEntity<List<ConfiguracionLSTM>>(configuraciones,HttpStatus.OK);
	       
	    }
	   
	   @GetMapping("procesoActivo")
	    public ResponseEntity<Boolean>  procesoActivo () throws Exception {
		   	return new ResponseEntity<Boolean>(this.iniciadorScript.procesoActivo(),HttpStatus.OK);
	       
	    }
	   
	  
	   @GetMapping("obtenerCurva/{imageName}")
	    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
	        // Ruta de la imagen en el sistema de archivos
	        Path imagePath = Paths.get("", imageName);
	        
	        // Leer la imagen como un array de bytes
	        byte[] imageBytes = Files.readAllBytes(imagePath);

	        // Establecer el tipo de contenido en la cabecera de la respuesta
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG); // Cambia a IMAGE_PNG si es una imagen PNG

	        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
	    }
	   
	   
	   private String pasarString(List<Ubicacion> ubicaciones) {
		   
		   String ubicacionesAString = "[";

		   for(Ubicacion ubicacion : ubicaciones) {
			   ubicacionesAString+=ubicacion.toString()+",";
		   }
		   return ubicacionesAString;
		   
	   }
	   
	   
	
	
}
