package vdg.model.controladorUbicaciones;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vdg.controller.RestriccionPerimetralController;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.Ubicacion;
import vdg.model.logica.CalculadorDistancias;

@Component
public class ControladorDistancias {

	@Autowired
	private RestriccionPerimetralController restriccionController;

	@Autowired
	private ControladorInfracciones controladorInfracciones;

	private List<RestriccionPerimetral> restricciones;

	public void calcularDistancias(Map<Integer, Ubicacion> ubicaciones) {
		// TRAIGO LAS RESTRICCIONES
		this.restricciones = this.restriccionController.listar();
		System.out.println("TENGO "+restricciones.size()+" RESTRICCIONES");
		// RECORRO LAS RESTRICCIONES PERIMETRALES
		for (RestriccionPerimetral r : restricciones) {
			// OBTENGO LAS UBICACIONES DE LAS PERSONAS DE LA RESTRICCION PERIMETRAL
			Ubicacion ubicacionVictimario = ubicaciones.get(r.getIdVictimario());
			Ubicacion ubicacionDamnificada = ubicaciones.get(r.getIdDamnificada());

			// GENERO LA DISTANCIA ENTRE LAS DOS UBICACIONES Y COMPARO CON LA DISTANCIA DE
			// LA RESTRICCION
			int distancia = generarDistancias(ubicacionVictimario, ubicacionDamnificada).intValue();
			if (distancia <= r.getDistancia()) {
				// GENERO O MODIFICO LA INFRACCION
				controlarInfraccion(distancia, r.getIdRestriccion());
			}
		}
		System.out.println("TERMINE DE VER LAS RESTRICCIONES A LAS "+new Date());
	}

	public Double generarDistancias(Ubicacion ubicacionVictimario, Ubicacion ubicacionDamnificada) {
		return CalculadorDistancias.obtenerDistancia(ubicacionVictimario.getLatitud(),
				ubicacionVictimario.getLongitud(), ubicacionDamnificada.getLatitud(),
				ubicacionDamnificada.getLongitud());
	}

	// CONTROLA SI LA INFRACCION ESTA ACTIVA O NO PARA GENERAR O MODIFICAR
	public void controlarInfraccion(int distancia, int idRestriccion) {
		controladorInfracciones.controlarInfraccionActiva(distancia, idRestriccion);
	}

}
