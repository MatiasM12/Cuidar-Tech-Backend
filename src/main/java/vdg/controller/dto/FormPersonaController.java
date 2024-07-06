package vdg.controller.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

import vdg.controller.DireccionController;
import vdg.controller.FotoIdentificacionController;
import vdg.controller.PersonaController;
import vdg.controller.UbicacionController;
import vdg.controller.UsuarioController;
import vdg.model.domain.Direccion;
import vdg.model.domain.Persona;
import vdg.model.domain.RolDeUsuario;
import vdg.model.domain.Ubicacion;
import vdg.model.domain.Usuario;
import vdg.model.dto.ErrorDTO;
import vdg.model.dto.FormPersonaDTO;
import vdg.model.validadores.ValidadoresFormPersona;

@RestController
@RequestMapping("/FormABMPersona")
@CrossOrigin
public class FormPersonaController {
	@Autowired
	PersonaController personaController;
	@Autowired
	UsuarioController usuarioController;
	@Autowired
	DireccionController direccionController;
	@Autowired
	UbicacionController ubicacionController;
	@Autowired
	FotoIdentificacionController fotoController;
	@Autowired
	ValidadoresFormPersona validador = new ValidadoresFormPersona();

//	@GetMapping
	public List<Persona> listar() {
		return personaController.listar();
	}

	@PostMapping
	public ErrorDTO agregar(@RequestBody vdg.model.dto.FormPersonaDTO personaDTO) {
		
		Persona persona = personaDTO.getPersona();
		Usuario usuario = personaDTO.getUsuario();
		Direccion direccion = personaDTO.getDireccion();
		Ubicacion ubicacion = new Ubicacion();

		//Valido el usuario y la persona. La dirección se valida en el front
		ErrorDTO error = validador.validarAgregarPersona(persona, usuario);
		if(error.getHayError()) {
			return error;
		}
		//Si los datos son válidos, paso a crear el usuario, direccion y persona, luego FOTO.
		
		usuarioController.agregar(usuario);
		int idUsuarioCreado = usuarioController.findByEmail(usuario.getEmail()).getIdUsuario();

		// BUSCAR ID DE LOCALIDAD Y ASIGNARLO A LA DIRECCIÓN
		direccionController.agregar(direccion);
		int idDireccionCreada = direccionController.getId(direccion);

		persona.setIdUsuario(idUsuarioCreado);
		persona.setIdDireccion(idDireccionCreada);
		personaController.agregar(persona);
		
		int idPersonaCreada = 0;
		if(usuario.getRolDeUsuario().equals(RolDeUsuario.DAMNIFICADA))
			idPersonaCreada = personaController.getDamnificadaByDni(persona.getDNI()).getIdPersona();
		else
			idPersonaCreada = personaController.getVictimarioByDni(persona.getDNI()).getIdPersona();
		
		// AGREGAR FOTO DE PERFIL
		fotoController.agregar(personaDTO.getFoto(), idPersonaCreada);
		
		
		ubicacion.setIdPersona(idPersonaCreada);
		ubicacion.setLatitud(personaDTO.getLat());
		ubicacion.setLongitud(personaDTO.getLon());
		ubicacion.setFecha(Timestamp.valueOf(LocalDateTime.now()));
		this.ubicacionController.agregar(ubicacion);
		
		return error;
	}

	@DeleteMapping("/{id}")
	public ErrorDTO borrar(@PathVariable("id") int id) {
		Persona p = personaController.getById(id);
		int idUsuario = p.getIdUsuario();
		int idDireccion = p.getIdDireccion();
		Ubicacion idUbicacion = ubicacionController.obtenerUbicacionDePersona(id);
		ErrorDTO ret = validador.validarBorrarPersona(p);
		if (ret.getHayError()) {
			return ret;
		}
		Usuario user = usuarioController.findByIdUsuario(idUsuario);
		if (user.getRolDeUsuario().equals(RolDeUsuario.VICTIMARIO)) {
			fotoController.eliminar(id);
		}

		ubicacionController.borrar(idUbicacion.getIdUbicacion());
		personaController.borrar(id);
		usuarioController.borrar(idUsuario);
		direccionController.borrar(idDireccion);
		return ret;
	}

	@GetMapping
	public List<FormPersonaDTO> getPersonasDTO(){
		List<FormPersonaDTO> listaPersonaDTO = new ArrayList<FormPersonaDTO>();
		
		for(Persona persona: personaController.listar()) {
			FormPersonaDTO personaDTO = new FormPersonaDTO();
			Usuario usuario = new Usuario();
			Direccion direccion = new Direccion();
			usuario = usuarioController.findByIdUsuario(persona.getIdUsuario());
			direccion = direccionController.findByIdDireccion(persona.getIdDireccion());
			//SETEO
			personaDTO.setPersona(persona);
			personaDTO.setUsuario(usuario);
			personaDTO.setDireccion(direccion);
			listaPersonaDTO.add(personaDTO);
		}
		
		return listaPersonaDTO;
	}
	
	@PutMapping("/ModificarPersona")
	public FormPersonaDTO modificarPersonaDTO(@RequestBody FormPersonaDTO personaDTO) {
		usuarioController.modificarUsuario(personaDTO.getUsuario());
		personaController.modificarPersona(personaDTO.getPersona());
		direccionController.modificarDireccion(personaDTO.getDireccion());
		return personaDTO;
	}
	
}
