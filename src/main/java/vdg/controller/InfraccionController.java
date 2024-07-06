package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.EstadoNotificacion;
import vdg.model.domain.Infraccion;
import vdg.model.domain.Notificacion;
import vdg.repository.InfraccionRepositoy;
import vdg.repository.NotificacionRepository;

@RestController
@RequestMapping("/Infraccion")
@CrossOrigin
public class InfraccionController {
	
	@Autowired
	private InfraccionRepositoy infraccionRepo;
	
	@Autowired
	private RestriccionPerimetralController restriccionController;
	
	@Autowired
	private NotificacionRepository notificacionRepo;

	@GetMapping
	public List<Infraccion> listar(){
		return infraccionRepo.findAll();
	}
	
	@GetMapping("/{idRestriccion}")
	public List<Infraccion> listarPorRestriccion(@PathVariable("idRestriccion") int idRestriccion){
		return infraccionRepo.findByIdRestriccion(idRestriccion);
	}
	
	@PostMapping
	public Infraccion agregar(@RequestBody Infraccion infraccion) {
		generarNotificacionDamnificada(infraccion);
		return infraccionRepo.save(infraccion);
	}
	
	private void generarNotificacionDamnificada(Infraccion infraccion) {
		int idDamnificada = restriccionController.getByIdRestriccion(infraccion.getIdRestriccion()).getIdDamnificada();

		Notificacion notificacion = new Notificacion();
		notificacion.setEstado(EstadoNotificacion.NoVista);
		notificacion.setAsunto("Nueva infracción");
		notificacion.setDescripcion("Se generó una nueva infracción a la restricción perimetral");
		notificacion.setFecha(infraccion.getFecha());
		notificacion.setIdUsuario(idDamnificada);
		
		notificacionRepo.save(notificacion);

	}

	public Infraccion getUltimaInfraccion(int idRestriccion) {
		return infraccionRepo.getUltimaInfraccion(idRestriccion);
	}

}
