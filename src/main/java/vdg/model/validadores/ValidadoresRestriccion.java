package vdg.model.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vdg.controller.PersonaController;
import vdg.controller.UsuarioController;
import vdg.model.domain.Persona;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.RolDeUsuario;
import vdg.model.domain.Usuario;
import vdg.model.dto.ErrorDTO;

@Component
public class ValidadoresRestriccion {

	@Autowired
	private PersonaController personaController;
	@Autowired
	private UsuarioController usuarioController;

	public ErrorDTO validarAltaRestriccion(RestriccionPerimetral restriccion) {
		ErrorDTO ret = new ErrorDTO();

		Persona damnificada = personaController.getById(restriccion.getIdDamnificada());
		Persona victimario = personaController.getById(restriccion.getIdVictimario());

		Usuario usuarioDamnificada = usuarioController.findByIdUsuario(damnificada.getIdUsuario());
		Usuario usuarioVictimario = usuarioController.findByIdUsuario(victimario.getIdUsuario());
		Usuario usuario = usuarioController.findByIdUsuario(restriccion.getIdUsuario());
		if (!usuarioDamnificada.getRolDeUsuario().equals(RolDeUsuario.DAMNIFICADA)) {
			ret.setHayError();
			ret.addMensajeError("La persona seleccionada como Damnificada no es una persona del tipo Damnificada.");
		}
		if (!usuarioVictimario.getRolDeUsuario().equals(RolDeUsuario.VICTIMARIO)) {
			ret.setHayError();
			ret.addMensajeError("La persona seleccionada como Victimario no es una persona del tipo Victimario.");
		}
		if (!usuario.getRolDeUsuario().equals(RolDeUsuario.ADMINISTRATIVO)) {
			ret.setHayError();
			ret.addMensajeError("El administrativo seleccionado no es unu usuario del tipo Administrativo.");
		}
		return ret;
	}

}
