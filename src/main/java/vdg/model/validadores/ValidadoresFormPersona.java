package vdg.model.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vdg.model.domain.Persona;
import vdg.model.domain.Usuario;
import vdg.model.dto.ErrorDTO;

@Component
public class ValidadoresFormPersona {
	@Autowired
	ValidadoresPersona validadorPersona;
	@Autowired
	ValidadoresUsuario validadorUsuario = new ValidadoresUsuario();
	
	public ErrorDTO validarAgregarPersona(Persona persona, Usuario usuario) {
		ErrorDTO ret = new ErrorDTO();
		
		if(!validadorPersona.validarAltaPersona(persona)) {
			ret.addMensajeError("Ya existe una persona con ese DNI.");
			ret.setHayError();
		}
		if(!validadorUsuario.validarAltaUsuario(usuario)) {
			ret.addMensajeError("Ya existe un usuario con ese MAIL.");
			ret.setHayError();
		}
		return ret;
		
	}
	
	public ErrorDTO validarBorrarPersona(Persona persona) {
		ErrorDTO ret = new ErrorDTO();
		
		if(!validadorPersona.validarBajaPersona(persona)) {
			ret.addMensajeError("No se puede borrar la persona seleccionada. Esta asignada a una restricción perimetral.");
			ret.setHayError();
		}
		return ret;
	}

}
