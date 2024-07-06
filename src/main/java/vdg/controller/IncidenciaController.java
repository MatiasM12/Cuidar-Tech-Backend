package vdg.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.controladorNotificaciones.GeneradorNotificaciones;
import vdg.model.domain.Incidencia;
import vdg.model.domain.Notificacion;
import vdg.model.domain.Peligrosidad;
import vdg.model.domain.Persona;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.Usuario;
import vdg.repository.IncidenciaRepository;
import vdg.repository.NotificacionRepository;
import vdg.repository.PersonaRepository;
import vdg.repository.RestriccionPerimetralRepository;
import vdg.repository.UsuarioRepository;

@RestController
@RequestMapping("/Incidencia")
@CrossOrigin
public class IncidenciaController {

	@Autowired
	private IncidenciaRepository incidenciaRepo;
	@Autowired
	private NotificacionRepository notificacionRepo;
	@Autowired
	private RestriccionPerimetralRepository restriccionController;
	@Autowired
	private PersonaRepository personaController;
	@Autowired
	private UsuarioRepository usuarioController;
	@Autowired
	private GeneradorNotificaciones generadorNotificaciones;

	

	@GetMapping("/{idRestriccion}/{cantidad}")
	public List<Incidencia> listar(@PathVariable("idRestriccion") int idRestriccion, @PathVariable("cantidad") int cantidad) {
		List<Incidencia> incidencias = incidenciaRepo.findByIdRestriccionOrderByFechaDesc(idRestriccion, cantidad);
		
		List<Incidencia> incidenciasModificadas = modificarIncidencias(incidencias);
		
		//return incidenciaRepo.findByIdRestriccionOrderByFechaDesc(idRestriccion, cantidad);
		return incidenciasModificadas;
	}
	
	@GetMapping
	public List<Incidencia> listar() {
		return incidenciaRepo.findAll();
	}
	
	public List<Incidencia> getIncidenciasIlocalizable(int idRestriccion){
		return incidenciaRepo.getIlocalizable(idRestriccion);
	}
	
	
	@GetMapping("/DamnificadaIlocalizable/{idRestriccion}")
	public List<Incidencia> getIncidenciasDamnificadaIlocalizable( @PathVariable("idRestriccion")int idRestriccion){
		return incidenciaRepo.getDamnificadaIlocalizable(idRestriccion);
	}
	
	
	@GetMapping("/VictimarioIlocalizable/{idRestriccion}")
	public List<Incidencia> getIncidenciasVictimarioIlocalizable(@PathVariable("idRestriccion") int idRestriccion){
		return incidenciaRepo.getVictimarioIlocalizable(idRestriccion);
	}
	
	@GetMapping("/PruebaDeVidaFallida/{idRestriccion}")
	public List<Incidencia> getIncidenciasPruebaDeVidaFallida(@PathVariable("idRestriccion") int idRestriccion){
		return incidenciaRepo.getPruebaDeVidaFallida(idRestriccion);
	}
	
	@GetMapping("/InfraccionDeRestriccion/{idRestriccion}")
	public List<Incidencia> getIncidenciasInfraccionDeRestriccion(@PathVariable("idRestriccion") int idRestriccion){
		return incidenciaRepo.getInfraccionDeRestriccion(idRestriccion);
	}
	
	@GetMapping("/FueraDeRutina/{idRestriccion}")
	public List<Incidencia> getFueraDeRutina(@PathVariable("idRestriccion") int idRestriccion){
		return incidenciaRepo.getFueraDeRutina(idRestriccion);
	}
	
	@GetMapping("/PeligrosidadA/{idRestriccion}")
	public List<Incidencia> getPeligrosidadA(@PathVariable("idRestriccion") int idRestriccion){
		return incidenciaRepo.getPeligrosidadA(idRestriccion);
	}
	
	@GetMapping("/PeligrosidadM/{idRestriccion}")
	public List<Incidencia> getPeligrosidadM(@PathVariable("idRestriccion") int idRestriccion){
		return incidenciaRepo.getPeligrosidadM(idRestriccion);
	}
	
	@GetMapping("/PeligrosidadB/{idRestriccion}")
	public List<Incidencia> getPeligrosidadB(@PathVariable("idRestriccion") int idRestriccion){
		return incidenciaRepo.getPeligrosidadB(idRestriccion);
	}

	
	
	public Incidencia getById(int idIncidencia) {
		return incidenciaRepo.findByIdIncidencia(idIncidencia);
	}
	
	
	@PostMapping("/agregar")
	public Incidencia agregar(@RequestBody Incidencia incidencia) {		
		//GUARDO LA INCIDENCIA
		Incidencia nuevaIncidencia = incidenciaRepo.save(incidencia);
		//CREO LA NOTIFICAICON CON LOS DATOS DE LA INCIDENCIA CREADA Y LA GUARDO
		RestriccionPerimetral restriccion = restriccionController.findByIdRestriccion(incidencia.getIdRestriccion());
		Persona damnificada = personaController.findById(restriccion.getIdDamnificada());
		Persona victimario = personaController.findById(restriccion.getIdVictimario());
		Usuario usuario = usuarioController.findAllByIdUsuario(restriccion.getIdUsuario()).get(0);
		

		
		Notificacion notificacion = generadorNotificaciones.generarNotificacion(incidencia, restriccion, damnificada, victimario, usuario);
	    notificacionRepo.save(notificacion);

	    return nuevaIncidencia;
	}
	
	

	
	
	
	
	public List<Incidencia> modificarIncidencias(List<Incidencia> incidencias) {
				
		for(Incidencia i : incidencias) {
			String desc = i.getDescripcion().replace("Maradon, Diego Armanda", "Agresor1").replace("Villafañe, Claudia", "Victima1");
			i.setDescripcion(desc);
		}
		return incidencias;
	}
	
}
