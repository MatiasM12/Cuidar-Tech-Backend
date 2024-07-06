package vdg.model.controladorUbicaciones;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vdg.controller.IncidenciaController;
import vdg.controller.InfraccionController;
import vdg.model.domain.EstadoInfraccion;
import vdg.model.domain.Incidencia;
import vdg.model.domain.Infraccion;
import vdg.model.domain.TipoIncidencia;

@Component
public class ControladorInfracciones {
	
	@Autowired
	private InfraccionController infraccionController;

	@Autowired
	private IncidenciaController incidenciaController;

	public ControladorInfracciones() {
		infraccionController = new InfraccionController();
		incidenciaController = new IncidenciaController();
	}
	
	public void controlarInfraccionActiva(int distancia, int idRestriccion){
		
		//TOMO LA ULTIMA INFRACCION DE LA RESTRICCION
		Infraccion ultimaInfraccion = new Infraccion();
		ultimaInfraccion = infraccionController.getUltimaInfraccion(idRestriccion);
		
		if(estaActiva(ultimaInfraccion))
			modificarInfraccionIncidencia(distancia, ultimaInfraccion);
		else
			generarInfraccionIncidencia(distancia, idRestriccion);
	}
	
	public boolean estaActiva(Infraccion ultimaInfraccion) {
		//OBTENGO LA FECHA ACTUAL PARA MODIFICAR LA INFRACCION		
		Date ahora = new Date();
		ahora.setTime(ahora.getTime()-60000);
		//CREO EL TIMESTAMP
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		//COMPARO LOS TIMESTAMP
		if(ultimaInfraccion == null)
			return false;
		if(ultimaInfraccion.getFecha().getTime() >= ahoraStamp.getTime())
			return true;
		else
			return false;
	}
	
	private void modificarInfraccionIncidencia(int distancia, Infraccion ultimaInfraccion) {
		//OBTENGO LA FECHA ACTUAL PARA LA INFRACCION		
		Date ahora = new Date();
		ahora.setTime(ahora.getTime());
		//CREO EL TIMESTAMP
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		
		ultimaInfraccion.setFecha(ahoraStamp);
		
		if(distancia>=ultimaInfraccion.getDistancia()) {
			ultimaInfraccion.setDistancia(distancia);
		}
		
		infraccionController.agregar(ultimaInfraccion);
	}

	public void generarInfraccionIncidencia(int distancia, int idRestriccion){
		//GENERO LA NUEVA INFRACCION Y SETEO LOS DATOS NECESARIOS
		Infraccion nuevaInfraccion = new Infraccion();
		//OBTENGO LA FECHA ACTUAL PARA LA INFRACCION		
		Date ahora = new Date();
		ahora.setTime(ahora.getTime());
		//CREO EL TIMESTAMP
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());

		nuevaInfraccion.setFecha(ahoraStamp);
		nuevaInfraccion.setDistancia(distancia);
		nuevaInfraccion.setEstadoInfraccion(EstadoInfraccion.Abierta);
		nuevaInfraccion.setIdRestriccion(idRestriccion);
		infraccionController.agregar(nuevaInfraccion);
		
		//GENERO INCIDENCIA Y SETEO FECHA, DESCRIPCION, TIPICO Y RESTRICCION
		Incidencia nuevaIncidencia = new Incidencia();
		nuevaIncidencia.setFecha(ahoraStamp);
		nuevaIncidencia.setDescripcion("Se cometió una infracción de: " + distancia + " metros");
		nuevaIncidencia.setTopico(TipoIncidencia.InfraccionDeRestriccion);
		nuevaIncidencia.setIdRestriccion(idRestriccion);
		incidenciaController.agregar(nuevaIncidencia);
	}
	
}
