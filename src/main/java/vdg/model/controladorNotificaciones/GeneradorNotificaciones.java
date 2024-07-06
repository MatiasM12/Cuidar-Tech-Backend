package vdg.model.controladorNotificaciones;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vdg.controller.UsuarioController;
import vdg.model.domain.EstadoNotificacion;
import vdg.model.domain.EstadoUsuario;
import vdg.model.domain.Incidencia;
import vdg.model.domain.Notificacion;
import vdg.model.domain.Persona;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.TipoIncidencia;
import vdg.model.domain.Usuario;
import vdg.repository.UsuarioRepository;

@Component
public class GeneradorNotificaciones {
	
    private static UsuarioRepository usuarioRepo;

    @Autowired
    public GeneradorNotificaciones(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }
	

    public static Notificacion generarNotificacion(Incidencia incidencia, RestriccionPerimetral restriccion,
                                                   Persona damnificada, Persona victimario, Usuario usuario) {
        Usuario destinatario = obtenerUsuarioDestinatario(incidencia, usuario);
        return crearNotificacionPorIncidencia(incidencia, restriccion, damnificada, victimario, destinatario);
    }

    private static Usuario obtenerUsuarioDestinatario(Incidencia incidencia, Usuario usuario) {
        if (usuario.getEstadoUsuario().equals(EstadoUsuario.CONECTADO)) {
            return usuario;
        } else {
            Usuario miembroDisponible = obtenerMiembroDisponibleDelGrupo(usuario.getIdGrupo());
            if (miembroDisponible != null) {
                return miembroDisponible;
            } else {
                throw new RuntimeException("No hay miembros disponibles en el grupo del usuario.");
            }
        }
    }

    private static Usuario obtenerMiembroDisponibleDelGrupo(int idGrupo) {
    	List<Usuario> listaUsuarios = usuarioRepo.findAll();
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getEstadoUsuario().equals(EstadoUsuario.CONECTADO) && usuario.getIdGrupo() == idGrupo) {
                return usuario;
            }
        }
        return null;
    }

	public static Notificacion crearNotificacionPorIncidencia(Incidencia incidencia, RestriccionPerimetral restriccion,
			Persona damnificada, Persona victimario, Usuario usuario) {

		Notificacion notificacion = new Notificacion();

		// Seteo del asunto y descripción dependiendo del tipoIncidencia
		if (incidencia.getTopico().equals(TipoIncidencia.DamnificadaIlocalizable)) {
			notificacion.setAsunto("Damnificada " + damnificada.nombreToString() + " ilocalizable");

			notificacion.setDescripcion("La damnificada " + damnificada.nombreToString()
					+ " está ilocalizable. Por favor Verifique las incidencias de la restriccion: "
					+ victimario.nombreToString() + " - " + damnificada.nombreToString());

		} else if (incidencia.getTopico().equals(TipoIncidencia.FueraDeRutina)) {
			notificacion.setAsunto("Victimario " + victimario.nombreToString() + " Fuera de rutina");

			notificacion.setDescripcion("El victimario " + victimario.nombreToString()
					+ " está fuera de rutina. Por favor Verifique las incidencias de la restriccion: "
					+ victimario.nombreToString() + " - " + damnificada.nombreToString());
			
		} else if (incidencia.getTopico().equals(TipoIncidencia.InfraccionDeRestriccion)) {
			notificacion.setAsunto(
					"Infracción en restricción " + victimario.nombreToString() + " - " + damnificada.nombreToString());

			notificacion.setDescripcion("El victimario " + victimario.nombreToString()
					+ " realizó una infracción a la restricción perimetral. Por favor Verifique las incidencias de la restriccion: "
					+ victimario.nombreToString() + " - " + damnificada.nombreToString());
			
		} else if (incidencia.getTopico().equals(TipoIncidencia.VictimarioIlocalizable)) {
			notificacion.setAsunto("Victimario " + victimario.nombreToString() + " ilocalizable");

			notificacion.setDescripcion("El victimario " + victimario.nombreToString()
					+ " está ilocalizable. Por favor Verifique las incidencias de la restriccion: "
					+ victimario.nombreToString() + " - " + damnificada.nombreToString());
		
		} else if (incidencia.getTopico().equals(TipoIncidencia.PruebaDeVidaFallida)) {
			notificacion.setAsunto("Fallo en prueba de vida de " + victimario.getApellido() + ", " + victimario.getNombre());

			notificacion.setDescripcion("Fallo en prueba de vida. Por favor Verifique las incidencias de la restriccion: "
					+ victimario.nombreToString() + " - " + damnificada.nombreToString());
		}

		// SETEO FECHA, Usuario y Estado DE LA NOTIFICACION
		notificacion.setFecha(new Timestamp(new Date().getTime()));
		notificacion.setEstado(EstadoNotificacion.NoVista);
		notificacion.setIdUsuario(usuario.getIdUsuario());

		return notificacion;
	}
}
