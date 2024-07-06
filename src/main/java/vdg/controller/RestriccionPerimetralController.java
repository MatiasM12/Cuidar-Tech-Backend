package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.controladorUbicaciones.ControladorDistancias;
import vdg.model.controladorUbicaciones.ControladorUbicaciones;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.dto.ErrorDTO;
import vdg.model.logica.SelectorAdministrativo;
import vdg.model.validadores.ValidadoresRestriccion;
import vdg.repository.GrupoRepository;
import vdg.repository.RestriccionPerimetralRepository;
import vdg.repository.UsuarioRepository;

@RestController
@RequestMapping("/RestriccionPerimetral")
@CrossOrigin
public class RestriccionPerimetralController {

	@Autowired
	private RestriccionPerimetralRepository restriccionPerimetralRepo;
	@Autowired
	private GrupoRepository grupo;
	@Autowired
	private UsuarioRepository usuario;
	
	@Autowired
	ValidadoresRestriccion validador = new ValidadoresRestriccion();
	
	@Autowired
	ControladorDistancias controladorDistancias = new ControladorDistancias();

	@Autowired
	ControladorUbicaciones controladorUbicaciones = new ControladorUbicaciones();
	
	@Autowired
	SelectorAdministrativo selectorAdminstrador;
	
	@GetMapping
	public List<RestriccionPerimetral> listar() {
		return restriccionPerimetralRepo.findAll();
	}

	@PostMapping
	public ErrorDTO agregar(@RequestBody RestriccionPerimetral restriccionPerimetral) throws Exception {
		try {
			RestriccionPerimetral res= selectorAdminstrador.seleccionarAdminstrativo(restriccionPerimetral, restriccionPerimetralRepo, usuario);
			ErrorDTO error = validador.validarAltaRestriccion(res);
			if(error.getHayError()) {
				return error;
			}
			restriccionPerimetralRepo.save(res);
			return error;
		
		}
		catch(Exception e) {
			
			System.out.println("Ocurrio un error y entre al catch");
			ErrorDTO error = new ErrorDTO();
			error.setHayError();
			error.addMensajeError(e.getMessage());
			return error;	
		}
		
		
	}

	@DeleteMapping("/borrar/{id}")
	public void borrar(@PathVariable("id") int id) {
		RestriccionPerimetral r = new RestriccionPerimetral();
		r.setIdRestriccion(id);
		restriccionPerimetralRepo.delete(r);
	}

	@GetMapping("/getByAdministrativo/{id}")
	public List<RestriccionPerimetral> getByAdministrativo(@PathVariable("id") int id) {
		return restriccionPerimetralRepo.findByIdUsuario(id);
	}

	@GetMapping("/getByDamnificada/{id}")
	public List<RestriccionPerimetral> getByDamnificada(@PathVariable("id") int idPersona) {
		return restriccionPerimetralRepo.findByIdDamnificada(idPersona);
	}

	@GetMapping("/getByVictimario/{id}")
	public List<RestriccionPerimetral> getByVictimario(@PathVariable("id") int idPersona) {
		return restriccionPerimetralRepo.findByIdVictimario(idPersona);
	}
	
	
	@GetMapping("/getByRestriccion/{id}")
	public RestriccionPerimetral getByIdRestriccion(@PathVariable("id") int idRestriccion) {
		return restriccionPerimetralRepo.findByIdRestriccion(idRestriccion);
	}
	
	@GetMapping("/ObtenerPorIdGrupo/{idGrupo}")
	public List<RestriccionPerimetral> getByIdGrupoRestriccion(@PathVariable("idGrupo") int idGrupo) {
		return restriccionPerimetralRepo.findAllByidGrupo(idGrupo);
	}
	

	public List<RestriccionPerimetral> getByPersona(int idPersona) {
		List<RestriccionPerimetral> ret = restriccionPerimetralRepo.findByIdVictimario(idPersona);
		if(ret.size() != 0)
			return ret;
		ret = restriccionPerimetralRepo.findByIdDamnificada(idPersona);
		return ret;
	}
	
	@PutMapping
	public RestriccionPerimetral modificarRestriccion(@RequestBody RestriccionPerimetral restriccion) {
		return restriccionPerimetralRepo.save(restriccion);
	}

	
}
