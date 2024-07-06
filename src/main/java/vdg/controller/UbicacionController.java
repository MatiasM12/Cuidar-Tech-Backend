package vdg.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.controladorRutina.UbicacionRutina;
import vdg.model.domain.Persona;
import vdg.model.domain.RPLugar;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.Ubicacion;
import vdg.model.domain.Usuario;
import vdg.model.dto.UbicacionDTO;
import vdg.model.logica.CalculadorDistancias;
import vdg.repository.RPLugarRepository;
import vdg.repository.UbicacionRepository;
import vdg.repository.UbicacionRutinaRepository;

@RestController
@RequestMapping("/Ubicacion")
@CrossOrigin
public class UbicacionController {

	@Autowired
	private UbicacionRepository ubicacionRepo;
	@Autowired
	private RestriccionPerimetralController restriccionController;
	@Autowired
	private UbicacionRutinaRepository ubicacionRutinaRepo;
	@Autowired
	private UbicacionRutinaController ubicacionRutinaController;
	@Autowired
	private PersonaController personaController;
	@Autowired
	private UsuarioController usuarioController;
	
	
	@GetMapping("/{idpersona}")
	public Ubicacion obtenerUbicacionDePersona(@PathVariable("idpersona") int idpersona){
		return this.ubicacionRepo.findByIdPersona(idpersona);
	}

	@GetMapping
	public List<Ubicacion> listar() {
		return ubicacionRepo.findAll();
	}

	@GetMapping("/getByRestriccion/{idRestriccion}")
	public UbicacionDTO findByRestriccion(@PathVariable("idRestriccion") int idRestriccion) {
		UbicacionDTO ubiDTO = new UbicacionDTO();
		RestriccionPerimetral restriccion = restriccionController.getByIdRestriccion(idRestriccion);
		ubiDTO.setUbicacionDamnificada(ubicacionRepo.findByIdPersona(restriccion.getIdDamnificada()));
		ubiDTO.setUbicacionVictimario(ubicacionRepo.findByIdPersona(restriccion.getIdVictimario()));
		return ubiDTO;
	}

	@PostMapping("/postUbi/{emailUsuario}")
	public ResponseEntity<String> agregar(@PathVariable("emailUsuario") String emailUsuario, @RequestBody Map<String, Double> posicion) {
		Usuario u = usuarioController.findByEmail(emailUsuario);
		Persona p = personaController.getByIdUsuario(u.getIdUsuario());
		Ubicacion ubicacion = new Ubicacion();
		ubicacion.setLatitud(BigDecimal.valueOf(posicion.get("latitud")).setScale(6, RoundingMode.HALF_UP));
		ubicacion.setLongitud(BigDecimal.valueOf(posicion.get("longitud")).setScale(6, RoundingMode.HALF_UP));
		ubicacion.setIdPersona(p.getIdPersona());
		ubicacion.setIdUbicacion(p.getIdPersona());
		Date ahora = new Date();
		ubicacion.setFecha(new Timestamp(ahora.getTime()));
		//chequearUbicacionRutina(ubicacion);
		ubicacionRepo.save(ubicacion);
		System.out.println("Guarde la ubicacion: "+ ubicacion.getLatitud() + ubicacion.getLongitud());
		return ResponseEntity.status(HttpStatus.OK).body("Ubicación agregada correctamente");
	}
	
	@PostMapping
	public Ubicacion agregar(@RequestBody Ubicacion ubicacion) {
		return ubicacionRepo.save(ubicacion);
	}
	

	@PutMapping("/{idUbicacion}")
	public Ubicacion modificar(@RequestBody Ubicacion ubicacion, @PathVariable("idUbicacion") int idUbicacion) {
		ubicacion.setIdUbicacion(idUbicacion);
		chequearUbicacionRutina(ubicacion);
		return ubicacionRepo.save(ubicacion);
	}
	

	@PostMapping("/infringe/{idRestriccion}")
	public boolean estaInfringiendo(@PathVariable("idRestriccion") int idRestriccion, @RequestBody UbicacionDTO ubicaciones) {
		RestriccionPerimetral restriccion = restriccionController.getByIdRestriccion(idRestriccion);
		Double distancia = CalculadorDistancias.obtenerDistancia(ubicaciones.getUbicacionDamnificada().getLatitud(),
				ubicaciones.getUbicacionDamnificada().getLongitud(), 
				ubicaciones.getUbicacionVictimario().getLatitud(), 
				ubicaciones.getUbicacionVictimario().getLongitud());
		return distancia<restriccion.getDistancia();
	}
	
	
	@PostMapping("/infringePasandoDistancia/{distancia}")
	public boolean infringePasandoDistancia(@PathVariable("distancia") int distanciaPasada, @RequestBody UbicacionDTO ubicaciones) {
		Double distancia = CalculadorDistancias.obtenerDistancia(ubicaciones.getUbicacionDamnificada().getLatitud(),
				ubicaciones.getUbicacionDamnificada().getLongitud(), 
				ubicaciones.getUbicacionVictimario().getLatitud(), 
				ubicaciones.getUbicacionVictimario().getLongitud());
		
		return distancia<distanciaPasada;
	}
	

	public List<Ubicacion> getPerdidasDeLocalizacion(Timestamp fechaLimite) {
		return ubicacionRepo.findIlocalizables(fechaLimite);
	}
	
	@SuppressWarnings("deprecation")
	private void chequearUbicacionRutina(Ubicacion ubicacion) {
		//FALTA CEQUEAR NULOS
		if((ubicacion.getFecha().getMinutes()>= 00 && ubicacion.getFecha().getMinutes()<= 05) || 
				(ubicacion.getFecha().getMinutes()>= 30 && ubicacion.getFecha().getMinutes()<= 35)) {
			//SI LA DIFERENCIA ES MAYOR DE 5 MINUTOS
			if(ubicacion.getFecha().getTime() -
					ubicacionRutinaRepo.findByPersonaAndFechaDesc(ubicacion.getIdPersona()).getFecha().getTime() >= 300000) {
				System.out.println("NO TENGO UBICACION RUTINARIA, ASI QUE GUARDO");
				UbicacionRutina ubicacionRutinanueva = new UbicacionRutina();
				ubicacionRutinanueva.setFecha(ubicacion.getFecha());
				ubicacionRutinanueva.setIdPersona(ubicacion.getIdPersona());
				ubicacionRutinanueva.setLatitud(ubicacion.getLatitud());
				ubicacionRutinanueva.setLongitud(ubicacion.getLongitud());
				
				ubicacionRutinaController.agregar(ubicacionRutinanueva);
			}
		}

	}

	@DeleteMapping("/{id}")
	public void borrar(@PathVariable("id") int id) {
		Ubicacion u = new Ubicacion();
		u.setIdUbicacion(id);
		ubicacionRepo.delete(u);
	}

}
