package vdg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EnumType;

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

import vdg.model.domain.EstadoUsuario;
import vdg.model.domain.RolDeUsuario;
import vdg.model.domain.Usuario;
import vdg.model.dto.ErrorDTO;
import vdg.model.email.EmailGateway;
import vdg.model.logica.Encriptar;
import vdg.model.validadores.ValidadoresUsuario;
import vdg.repository.ConfigMensajeRepository;
import vdg.repository.UsuarioRepository;

@RestController
@RequestMapping("/Usuario")
@CrossOrigin
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private ConfigMensajeRepository configMsjRepo;
	@Autowired
	private ValidadoresUsuario validador = new ValidadoresUsuario();
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepo.findEmpleados();
	}

	@PostMapping
	public ErrorDTO agregar(@RequestBody Usuario usuario) {
		ErrorDTO error = new ErrorDTO();
		if (!validador.validarAltaUsuario(usuario)) {
			error.setHayError();
			error.addMensajeError("Ya existe un usuario creado con ese MAIL");
			return error;
	}
	//	usuario.setContrasena(GeneradorContraseña.generarContraseña());
	//	String mensaje = "Se ha dado de alta un nuevo usuario para el sistema.\nSu contraseña es: "+usuario.getContrasena();	
		usuario.setContrasena(Encriptar.sha256(usuario.getContrasena()));
		//ENVIAR CONTRASEÑA POR MAIL

	//	EmailGateway.enviarMail(usuario.getEmail(), mensaje, "Nuevo usuario generado");
		usuarioRepo.save(usuario);
		return error;
		
	}

	@DeleteMapping("/{id}")
	public ErrorDTO borrar(@PathVariable("id") int id) {
		Usuario u = new Usuario();
		u.setIdUsuario(id);
		ErrorDTO error = new ErrorDTO();
		if (!validador.validarBajaUsuario(u)) {
			error.setHayError();
			error.addMensajeError("El Usuario tiene restricción perimetral asignada");
			return error;
		}
		usuarioRepo.delete(u);
		return error;
	}

	@GetMapping("/GetByEmail/{email}")
	public Usuario findByEmail(@PathVariable("email") String email) {
		List<Usuario> usuarios = usuarioRepo.findAllByEmail(email);
		return usuarios.isEmpty() ? null : usuarios.get(0);
	}

	@PostMapping("/login")
	public boolean login(@RequestBody Map<String, String> info) {

		Usuario user = findByEmail(info.get("email"));
		if (user == null)
			return false;
		if(!(user.getRolDeUsuario().equals(RolDeUsuario.ADMINISTRATIVO) || 
				user.getRolDeUsuario().equals(RolDeUsuario.SUPERVISOR) || 
				user.getRolDeUsuario().equals(RolDeUsuario.SUPERVISOR_GENERAL)))
			return false;
		if (user.getContrasena().equals(info.get("contrasena")))
			return true;
		return false;

	}
	
	@PostMapping("/loginApp")
	public RolDeUsuario loginApp(@RequestBody Map<String, String> info) {
		Usuario user = findByEmail(info.get("email"));
		if (user == null)
			return null;
		if(!(user.getRolDeUsuario().equals(RolDeUsuario.VICTIMARIO) || 
				user.getRolDeUsuario().equals(RolDeUsuario.DAMNIFICADA)))
			return null;
		if (user.getContrasena().equals(info.get("contrasena")))
			return user.getRolDeUsuario();
		return null;

	}

	public Usuario findByIdUsuario(int idUsuario) {
		List<Usuario> usuarios = usuarioRepo.findAllByIdUsuario(idUsuario);
		return usuarios.isEmpty() ? null : usuarios.get(0);
	}
	
	@GetMapping("/{id}")
	public Usuario findByIdUsuarios(@PathVariable("id") int id) {
		List<Usuario> usuarios = usuarioRepo.findAllByIdUsuario(id);
		return usuarios.isEmpty() ? null : usuarios.get(0);
	}
	
	
	@GetMapping("porGrupo/{id}")
	public Usuario obtenerPorIdgrupo(@PathVariable("id") int id) {
		List<Usuario> usuarios = usuarioRepo.findAllByIdGrupo(id);
		return usuarios.isEmpty() ? null : usuarios.get(0);
	}
	
	
	@PutMapping("/recuperarContrasena")
	public ErrorDTO recuperarContrasena(@RequestBody Usuario usuario) {
		ErrorDTO error = new ErrorDTO();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = usuarioRepo.findAllByEmail(usuario.getEmail()); 
		if(usuarios.size()!=0) {
			usuario = usuarios.get(0);
			int contrasena = (int) Math.floor(Math.random()*9999+1);
			String mensaje = configMsjRepo.findByTipo("passMail").getMensajeBef()+"\n "+contrasena +"\n" + configMsjRepo.findByTipo("passMail").getMensajeAft();
			System.out.println("------------------------------------------------ "+contrasena+" ------------------------------------");
			EmailGateway.enviarMail(usuario.getEmail(), mensaje, configMsjRepo.findByTipo("passMail").getAsunto());
			String contrasenaEncriptada = Encriptar.sha256(""+contrasena);
			usuario.setContrasena(contrasenaEncriptada);
			usuarioRepo.save(usuario);
			return error;

		}
		else{
			error.setHayError();
			error.addMensajeError("El email no corresponde a un usuario");
			return error;
		}
		 
	}
	
	@PutMapping("/modificarUsuario")
	public Usuario modificarUsuario(@RequestBody Usuario usuario) {
		System.out.println(usuario.getEstadoUsuario());
		System.out.println("Llegue al put");

		return usuarioRepo.save(usuario);
	}
	
	
	@GetMapping("/estadoUsuario/{estadoUsuario}")
	public List<Usuario> findByEstadoUsuarios(@PathVariable("estadoUsuario") EstadoUsuario estadoUsuario) {
		List<Usuario> usuarios = usuarioRepo.findAllByestadoUsuario(estadoUsuario);
		return usuarios;
	}
	
	@GetMapping("/rolDeUsuario/{rolDeUsuario}")
	public List<Usuario> findByRolUsuarios(@PathVariable("rolDeUsuario") RolDeUsuario rolDeUsuario) {
		List<Usuario> usuarios = usuarioRepo.findAllByrolDeUsuario(rolDeUsuario);
		return usuarios;
	}
	
	
	
	@GetMapping("/obtenerUsuarioPorGrupo/{idgrupo}")
	public List<Usuario> obtenerUsuariosPorIDGrupo(@PathVariable("idgrupo") int idGrupo) {
		return usuarioRepo.findAllByIdGrupo(idGrupo);
		 
	}

}
