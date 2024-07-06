package vdg.model.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vdg.controller.RestriccionPerimetralController;
import vdg.controller.UsuarioController;
import vdg.model.domain.Usuario;

@Component
public class ValidadoresUsuario {

	@Autowired
	private UsuarioController usuarioController;
	@Autowired
	private RestriccionPerimetralController restriccionController;
	
	public boolean validarAltaUsuario(Usuario usuario) {
		return !existeUsuario(usuario);
	}

	public boolean validarBajaUsuario(Usuario usuario) {
		return !tieneAsignadaRestriccion(usuario);
	}

	private boolean existeUsuario(Usuario usuario) {
		return usuarioController.findByEmail(usuario.getEmail()) != null;
	}
	
	private boolean tieneAsignadaRestriccion(Usuario usuario) {
		return !restriccionController.getByAdministrativo(usuario.getIdUsuario()).isEmpty();
	}
}
