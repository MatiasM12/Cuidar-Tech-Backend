package vdg.model.controladorIncidencias;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vdg.controller.IncidenciaController;
import vdg.controller.PersonaController;
import vdg.controller.RestriccionPerimetralController;
import vdg.controller.UbicacionController;
import vdg.controller.UsuarioController;
import vdg.model.domain.Incidencia;
import vdg.model.domain.Persona;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.RolDeUsuario;
import vdg.model.domain.TipoIncidencia;
import vdg.model.domain.Ubicacion;
import vdg.model.domain.Usuario;

@Component
public class ControladorIncidencias {
	@Autowired
	private UbicacionController ubicacionController;
	@Autowired
	private IncidenciaController incidenciaController;
	@Autowired
	private RestriccionPerimetralController restriccionController;
	@Autowired
	private PersonaController personaController;
	@Autowired
	private UsuarioController usuarioController;

	public void controlarIlocalizables(Timestamp fechaLimite) {
		// OBTENER LAS UBICACIONES DE LAS PERSONAS A LAS QUE SE PERDIÓ LA LOCALIZACIÓN
		List<Ubicacion> localizacionesPerdidas = ubicacionController.getPerdidasDeLocalizacion(fechaLimite);
		// CREO UN TIME STAMP DE HACE 10 MIN PARA VER SI LA ULTIMA INCIDENCIA FUE ANTES
		Date ahora = new Date();
		ahora.setTime(ahora.getTime() - 600000);
		Timestamp haceDiezMinutos = new Timestamp(ahora.getTime());

		//RECORRO TODAS LAS UBICACIONES DE LAS PERSONAS A LAS QUE NO SE LOCALIZA
		for (Ubicacion ubicacion : localizacionesPerdidas) {
			// LA PERSONA ILOCALIZABLE LA TRAIGO, Y TRAIGO EL USUARIO NECESITO EL ROL DE ESTE
			Persona personaIlocalizable = personaController.getById(ubicacion.getIdPersona());
			Usuario usuarioIlocalizable = usuarioController.findByIdUsuario(personaIlocalizable.getIdUsuario());

			// Obtengo una restriccion de la persona para obtener una incidencia
			RestriccionPerimetral restriccion = restriccionController.getByPersona(ubicacion.getIdPersona()).get(0);
			
			// Obtengo las incidencias ilocalizables de esa persona de la ubicacion
			//List<Incidencia> i = incidenciaController.getIncidenciasIlocalizable(restriccion.getIdRestriccion());
			
			//OBTENGO LAS INCIDENCIAS DE ESA PERSONA ILOCALIZABLE, SEGUN EL ROL DE USUARIO
			List<Incidencia> incidencias;
			if(usuarioIlocalizable.getRolDeUsuario().equals(RolDeUsuario.DAMNIFICADA))
				incidencias = incidenciaController.getIncidenciasDamnificadaIlocalizable(restriccion.getIdRestriccion());
			else
				incidencias = incidenciaController.getIncidenciasVictimarioIlocalizable(restriccion.getIdRestriccion());

			// SI NO EXISTE INCIDENCIA, O LA ULTIMA GENERADA FUE HACE MAS DE 10 MIN, GENERO
			// INCIDENCIA
			if (incidencias.size() == 0 || incidencias.get(0).getFecha().before(haceDiezMinutos)) {
				generarIncidenciaIlocalizable(ubicacion);
			}
		}
	}

	private void generarIncidenciaIlocalizable(Ubicacion ubicacion) {
		//CREO EL TIMESTAMP PARA SETEARLE A LA INCIDENCIA
		Date ahora = new Date();
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		
		// OBTENGO LAS RESTRICCIONES DE ESA PERSONA PARA GENERARLE INCIDENCIAS, Y LA
		// PERSONA PARA USAR SUS DATOS COMO DESCRIPCION DE INCIDENCIA
		List<RestriccionPerimetral> restriccionesPersona = restriccionController.getByPersona(ubicacion.getIdPersona());
		Persona persona = personaController.getById(ubicacion.getIdPersona());

		// CREO EL TOPICO DE LA INCIDENCIA
		TipoIncidencia tipoIncidencia = TipoIncidencia.VictimarioIlocalizable;
		// CREO LA DESCRIPCION DE LA INCIDENCIA
		String descripcion = "Se perdió la localización de la persona ";

		// ME FIJO SI ES DAMNIFICADA O VICTIMARIO Y LO USO EN DESCRIPCION Y TIPO
		// INCIDENCIA
		if (restriccionesPersona.get(0).getIdDamnificada() == persona.getIdPersona()) {
			descripcion += "damnificada ";
			tipoIncidencia = TipoIncidencia.DamnificadaIlocalizable;
		} else
			descripcion += "victimario ";
		
		descripcion += persona.getApellido() + ", " + persona.getNombre() + ". Su última localización fue el "
				+ ubicacion.getFecha();
		
		// RECORRO TODAS LAS RESTRICCIONES DE LA PERSONA PARA GENERAR INCIDENCIAS
		for (RestriccionPerimetral restriccion : restriccionesPersona) {
			// CREO LA INCIDENCIA Y LE SETEO LOS VALORES
			Incidencia incidencia = new Incidencia();
			incidencia.setFecha(ahoraStamp);
			incidencia.setDescripcion(descripcion);
			incidencia.setTopico(tipoIncidencia);
			incidencia.setIdRestriccion(restriccion.getIdRestriccion());
			// HAGO EL POST
			incidenciaController.agregar(incidencia).getFecha();
			
		}
	}

}
