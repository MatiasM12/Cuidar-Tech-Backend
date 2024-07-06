package vdg.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.domain.EstadoNotificacion;
import vdg.model.domain.Notificacion;
import vdg.model.domain.Usuario;
import vdg.repository.NotificacionRepository;
import vdg.repository.UsuarioRepository;

@RestController
@RequestMapping("/Notificacion")
@CrossOrigin
public class NotificacionController {

	@Autowired
	private NotificacionRepository notificacionRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;

	

	@GetMapping("/{idUsuario}")
	public List<Notificacion> listar(@PathVariable("idUsuario") int idUsuario) {
		return notificacionRepo.findByIdUsuario(idUsuario);
	}

	@GetMapping("/App/{emailUsuario}")
	public List<Notificacion> getNotificacionesApp(@PathVariable("emailUsuario") String emailUsuario) {
		Usuario user = new Usuario();
		user = usuarioRepo.findAllByEmail(emailUsuario).get(0);
		return notificacionRepo.findByIdUsuario(user.getIdUsuario());
	}
	
	@GetMapping("/getNotificaciones/{idAdm}/{cantidad}")
	public List<Notificacion> getNotificaciones(@PathVariable("idAdm") int idAdm, @PathVariable("cantidad") int cantidad) {
		return notificacionRepo.findVistasNoVistas(idAdm, cantidad);
	}

	@GetMapping("/getArchivadas/{idAdm}/{cantidad}")
	public List<Notificacion> getArchivadas(@PathVariable("idAdm") int idAdm, @PathVariable("cantidad") int cantidad) {
		return notificacionRepo.findArchivadas(idAdm, cantidad);
	}
	
	@GetMapping("/getCantNoVistas/{emailUsuario}")
	public int getCantNoVistas(@PathVariable("emailUsuario") String emailUsuario) {
		int idUsuario = usuarioRepo.findAllByEmail(emailUsuario).get(0).getIdUsuario();
		return notificacionRepo.countByEstadoNotificacionAndIdUsuario(EstadoNotificacion.NoVista,idUsuario);
	}
	
	@GetMapping("/getNoVistas/{emailUsuario}")
	public List<Notificacion> getNoVistas(@PathVariable("emailUsuario") String emailUsuario) {
		int idUsuario = usuarioRepo.findAllByEmail(emailUsuario).get(0).getIdUsuario();
		List<Notificacion> novistas = notificacionRepo.findByEstadoNotificacionAndIdUsuario(EstadoNotificacion.NoVista,idUsuario);
		for(Notificacion notificacion: novistas) {
			this.setVista(notificacion.getIdNotificacion());
		}
		return novistas;
	}
	
	@PostMapping("/archivar")
	public Notificacion archivar(@RequestBody int idNotificacion) {
		Notificacion notificacion = notificacionRepo.findByIdNotificacion(idNotificacion);
		notificacion.setEstado(EstadoNotificacion.Archivada);
		notificacionRepo.save(notificacion);
		return notificacion;
	}
	
	@PostMapping("/setVista")
	public Notificacion setVista(@RequestBody int idNotificacion) {
		Notificacion notificacion = notificacionRepo.findByIdNotificacion(idNotificacion);
		notificacion.setEstado(EstadoNotificacion.Vista);
		notificacionRepo.save(notificacion);
		return notificacion;
	}
	
	@PutMapping("/marcarTodasComoVistas/{emailUsuario}")
	public ResponseEntity<String> marcarTodasComoVistas(@PathVariable("emailUsuario")  String emailUsuario) {
	    try {
	        List<Usuario> usuarios = usuarioRepo.findByEmailIgnoreCase(emailUsuario);
	        if (usuarios.isEmpty()) {
	            return ResponseEntity.status(404).body("Usuario no encontrado.");
	        }
	        Usuario user = usuarios.get(0);
	        notificacionRepo.marcarTodasComoVistas(user.getIdUsuario());
	        return ResponseEntity.ok("Todas las notificaciones marcadas como vistas.");
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body("Error al marcar todas las notificaciones como vistas: " + e.getMessage());
	    }
	}

		
}
