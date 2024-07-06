package vdg.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import antlr.collections.List;
import springfox.documentation.spring.web.json.Json;
import vdg.model.api.NormalizacionCoordenadas;
import vdg.model.domain.BotonAntipanico;
import vdg.model.domain.Comisaria;
import vdg.model.domain.Contacto;
import vdg.model.domain.DependenciaGenero;
import vdg.model.domain.FotoIdentificacion;
import vdg.model.domain.Incidencia;
import vdg.model.domain.Persona;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.Ubicacion;
import vdg.model.domain.UbicacionNormalizada;
import vdg.model.domain.Usuario;
import vdg.model.email.EmailGateway;
import vdg.model.logica.PuntoCercano;
import vdg.model.notificacionesTerceros.CuerpoMensaje;
import vdg.model.notificacionesTerceros.CuerpoNotificacion;
import vdg.model.notificacionesTerceros.TelegramNotificador;
import vdg.model.notificacionesTerceros.WpNotificador;
import vdg.repository.BotonAntipanicoRepository;
import vdg.repository.ComisariaRepository;
import vdg.repository.ConfigMensajeRepository;
import vdg.repository.ContactoRepository;
import vdg.repository.DependenciaRepository;
import vdg.repository.FotoIdentificacionRepository;
import vdg.repository.JuzgadoRepository;
import vdg.repository.RestriccionPerimetralRepository;
import vdg.repository.UbicacionRepository;


@RestController
@RequestMapping("/BotonAntipanico")
@CrossOrigin
public class BotonAntipanicoController {

	@Autowired
	private BotonAntipanicoRepository botonAntipanicoRepo;
	@Autowired
	private ContactoRepository contactoRepo;
	@Autowired
	private UsuarioController usuarioController;
	@Autowired
	private PersonaController personaController;
	@Autowired
	private WpNotificador  wpNotificador;
	@Autowired
	private NormalizacionCoordenadas normalizador;
	@Autowired PuntoCercano puntoMasCercano;
	@Autowired 
	private ComisariaRepository  comisariaRepo;
	@Autowired
    private  TelegramNotificador telegramNotificador;
	@Autowired
	private JuzgadoRepository juzgadoRepository;
	@Autowired
	private DependenciaRepository dependenciaRepository;
	@Autowired
	private RestriccionPerimetralRepository restriccionRepository;
	@Autowired
	private FotoIdentificacionRepository fotoRepository;
	@Autowired
	private UbicacionRepository ubicacion;
	@Autowired
	private ConfigMensajeRepository configMsjRepo;
	
	
	
	@Value("${servicioMensajeria}")
    private String mensajeria;
	
	@Value("${preview_url}")
    private Boolean preview_url;
	@Value("${recipient_type}")
	private String recipient_type;
	
	
	@Value("${tokenTelegram}")
	private String token;
	
	

	
	@PostMapping("/{emailDamnificada}")
	public BotonAntipanico alertar(@RequestBody BotonAntipanico botonAntipanico, @PathVariable("emailDamnificada") String emailDamnificada) {
		
		//TRAIGO LOS OBJETOS NECESARIOS
		Usuario usuario = usuarioController.findByEmail(emailDamnificada);
		Persona damnificada = personaController.getByIdUsuario(usuario.getIdUsuario());
		//GENERO TIMESTAMP Y LO SETEO
		Date ahora = new Date();
		ahora.setTime(ahora.getTime());
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		botonAntipanico.setFecha(ahoraStamp);
		
		botonAntipanico.setIdDamnificada(damnificada.getIdPersona());
		//ENVIO ALERTAS A LOS CONTACTOS DE LA DAMNIFICADA
		enviarMail(damnificada, botonAntipanico);
		return botonAntipanicoRepo.save(botonAntipanico);
	}
	
	
	
	/**Envia un mensaje al juzgado que coincida con el id del juzgado**/
	
	@PostMapping("/alertarJuzgado/{idJuzgado}/{idPerimetral}")
	public void alertarJuzgado(@PathVariable("idJuzgado") Integer idJuzgado,@PathVariable("idJuzgado") Integer idPerimetral) {
		Long idTelegram = this.juzgadoRepository.findByidJuzgado(idJuzgado).getIdJuzgadoTelegram();		
		this.telegramNotificador.enviarMensaje(idTelegram,configMsjRepo.findByTipo("alertaTelegram").getMensajeBef()+"\n" +"#"+idPerimetral +"\n" + configMsjRepo.findByTipo("alertaTelegram").getMensajeAft());
	}
	
	
	
	/**Envia un mensaje a la api de Whatsapp alertando a la policia
	 *@Returns CuerpoNotificacion
	 ***/
	@PostMapping("/alertarPolicia/{lat}/{lon}/{idrestriccion}")
	public Comisaria alertarPolicia(@PathVariable("lat") double lat,@PathVariable("lon")  double lon,@PathVariable("idrestriccion") int idrestriccion ) throws Exception {		
		RestriccionPerimetral res =	this.restriccionRepository.findByIdRestriccion(idrestriccion);
		UbicacionNormalizada respuesta = this.normalizador.ObtenerCoordenadas(lat, lon);
		String municipioAlerta = respuesta.getUbicacion().getMunicipio().getNombre();
		ArrayList<Comisaria> comisariasMunicipio = (ArrayList<Comisaria>) this.comisariaRepo.findAllBypartido(municipioAlerta);
		Comisaria comisaria = this.puntoMasCercano.puntoMasCercano(new BigDecimal(lat),new BigDecimal(lon),comisariasMunicipio);
		String nroTelefono = comisaria.getTelefono();
		if(comisaria.getIdComisariaTelegram()!=null) {
			FotoIdentificacion fotoVictima = this.fotoRepository.findByIdPersona(res.getIdDamnificada());
			FotoIdentificacion fotoAgresor = this.fotoRepository.findByIdPersona(res.getIdVictimario());
			cuerpoMensaje(idrestriccion,
					lat
				   ,lon
				   ,comisaria.getIdComisariaTelegram()
				   ,fotoVictima
				   ,fotoAgresor);
		}
			
		this.telegramNotificador.enviarMensaje(comisaria.getIdComisariaTelegram(), configMsjRepo.findByTipo("alertaTelegramP").getMensajeBef()+lat+ configMsjRepo.findByTipo("alertaTelegramP").getMensajeAft()+ lon);
		
		//this.wpNotificador.notificar(configurarCuerpo(nroTelefono));
		return comisaria;
	}

	
	
	/**Envia un mensaje a la api de Whatsapp alertando a la policia, se debe pasar por parametro la ciudad
	 *@Returns CuerpoNotificacion
	 ***/
	@PostMapping("/alertarPoliciaPorCiudad/{lat}/{lon}/{ciudad}/{idrestriccion}")
	public Comisaria alertarPoliciaDandoCiudad(@PathVariable("lat") double lat,@PathVariable("lon")  double lon,@PathVariable("ciudad")String ciudad,@PathVariable("idrestriccion") int idrestriccion) throws Exception {		
		RestriccionPerimetral res =	this.restriccionRepository.findByIdRestriccion(idrestriccion);
		ArrayList<Comisaria> comisariasMunicipio = (ArrayList<Comisaria>) this.comisariaRepo.findAllBypartido(ciudad);
		Comisaria comisaria = this.puntoMasCercano.puntoMasCercano(new BigDecimal(lat),new BigDecimal(lon),comisariasMunicipio);
		String nroTelefono = comisaria.getTelefono();
		
		if(comisaria.getIdComisariaTelegram()!=null) {
			FotoIdentificacion fotoVictima = this.fotoRepository.findByIdPersona(res.getIdDamnificada());
			FotoIdentificacion fotoAgresor = this.fotoRepository.findByIdPersona(res.getIdVictimario());
			cuerpoMensaje(idrestriccion,
					lat
				   ,lon
				   ,comisaria.getIdComisariaTelegram()
				   ,fotoVictima
				   ,fotoAgresor);
		}
		return comisaria;
	}
	
	

	/**Envia un mensaje a la api de telegram alertando a la dependencia de genero,que se debe pasar por parametro la ciudad
	 *@Returns CuerpoNotificacion
	 ***/
	@PostMapping("/alertarDependenciaPorCiudad/{idRestriccion}")
	public  ResponseEntity<DependenciaGenero> alertarDependencia(@PathVariable("idRestriccion") int idRestriccion) throws Exception {		
		RestriccionPerimetral res =	this.restriccionRepository.findByIdRestriccion(idRestriccion);
		Ubicacion ubicacion =	this.ubicacion.findByIdPersona(res.getIdDamnificada());
		DependenciaGenero dependenciaAsignada = this.dependenciaRepository.findByidDependencia(res.getIdDependenciaGenero());
		
				
		if(dependenciaAsignada==null||dependenciaAsignada.getIdComisariaTelegram()==null)
			return new ResponseEntity<>(dependenciaAsignada, HttpStatus.FAILED_DEPENDENCY);
		
		
		FotoIdentificacion fotoVictima = this.fotoRepository.findByIdPersona(res.getIdDamnificada());
		FotoIdentificacion fotoAgresor = this.fotoRepository.findByIdPersona(res.getIdVictimario());
		
		cuerpoMensaje(
				idRestriccion,
				ubicacion.getLatitud().doubleValue(), 
				ubicacion.getLongitud().doubleValue(),
				dependenciaAsignada.getIdComisariaTelegram(), 
				fotoVictima, fotoAgresor);	

		return  new ResponseEntity<>(dependenciaAsignada, HttpStatus.OK);
	}



	private void cuerpoMensaje(int idRestriccion, double lat,double lon, Long idTelegram,
			FotoIdentificacion fotoVictima, FotoIdentificacion fotoAgresor) {
		try {
		
		this.telegramNotificador.enviarFoto(idTelegram, new InputFile(this.convertirImagen(fotoVictima.getFoto()),"victima"));
		this.telegramNotificador.enviarFoto(idTelegram, new InputFile(this.convertirImagen(fotoAgresor.getFoto()),"Agresor"));
			}
		
		catch(Exception e) {
			this.telegramNotificador.enviarMensaje(idTelegram,"Error al enviar imagen");
			}	

		
		try {
			UbicacionNormalizada ubicacionNormalizada = this.normalizador.ObtenerCoordenadas(lat, lon);
			this.telegramNotificador.enviarMensaje(idTelegram ,configMsjRepo.findByTipo("alertaTelegram").getMensajeBef()+"\n" +"#"+idRestriccion +"\n" + configMsjRepo.findByTipo("alertaTelegram").getMensajeAft());
			}
			
			catch(Exception e) {
				this.telegramNotificador.enviarMensaje(idTelegram,configMsjRepo.findByTipo("alertaTelegram").getMensajeBef()+"\n" +"#"+idRestriccion +"\n" + configMsjRepo.findByTipo("alertaTelegram").getMensajeAft());
			}
	}
	
	
	
	
		/**
		 * Convierte las imagenes de byte[] a 
		 * @param imagen
		 * @return
		 * @throws IOException 
		 */
	
		private ByteArrayInputStream convertirImagen(byte[] imagen) throws IOException {	
		return new ByteArrayInputStream(imagen);
	}
	
	
	
	/**Configura el cuerpo a enviar a la api de Whatsapp
	 *@Returns CuerpoNotificacion
	 ***/
	private CuerpoNotificacion configurarCuerpo(String nroTelefono) throws Exception {
		CuerpoNotificacion cuerpo  = new CuerpoNotificacion();
		CuerpoMensaje cuerpoMensaje = new CuerpoMensaje();
		cuerpo.setMessaging_product(this.mensajeria);
		cuerpo.setTo(nroTelefono);
		cuerpo.setPreview_url(this.preview_url);
		cuerpo.setRecipient_type(this.recipient_type);
		cuerpoMensaje.setBody("Este es mi primer mensaje desde java");
		cuerpo.setCuerpoMensaje(cuerpoMensaje);
		return cuerpo;
	}
	
	
	
	public void enviarMail(Persona damnificada, BotonAntipanico botonAntipanico) {
		
		String asunto = configMsjRepo.findByTipo("alertaMail").getAsunto();
		
		String mensaje = damnificada.getApellido() + ", " + damnificada.getNombre() + 
				configMsjRepo.findByTipo("alertaMail").getMensajeBef() + botonAntipanico.getLatitud() +
				", " + botonAntipanico.getLongitud() + configMsjRepo.findByTipo("alertaMail").getMensajeAft() + botonAntipanico.getFecha();

		for(Contacto contacto: contactoRepo.findByIdDamnificada(damnificada.getIdPersona())) {
			EmailGateway.enviarMail(contacto.getEmail(), mensaje, asunto);			
		}
	}
	
	public void generarInicidencia(Persona damnificada, BotonAntipanico botonAntipanico) {
		//GENERO INCIDENCIA PERO TODAVIA NO LA GUARDO
		Incidencia incidencia = new Incidencia();
		incidencia.setFecha(botonAntipanico.getFecha());
		//GENERO NOTIFICACION Y LA GUARDO PARA QUE LA VEA EL USER
//		Notificacion notificacion = GeneradorNotificaciones.generarNotificacion(incidencia, restriccion, damnificada, victimario, usuario);
//		notificacionRepo.save(notificacion);
		
	}
	
	

	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Bean
	public HttpHeaders HttpHeaders() {
		HttpHeaders header = new HttpHeaders();
	    return header ;
	}
	
	@Bean RestTemplateBuilder restTemplateBuilder(){
		
		return new RestTemplateBuilder();
	}
	
	@Bean NormalizacionCoordenadas normalizacionCoordenadas(){
		return new NormalizacionCoordenadas(this.restTemplateBuilder());
	}
	
	
	
	
	
	
}
