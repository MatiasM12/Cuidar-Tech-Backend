package vdg.model.controladorUbicaciones;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vdg.controller.UbicacionController;
import vdg.model.controladorIncidencias.ControladorIncidencias;
import vdg.model.domain.Ubicacion;

@Component
public class ControladorUbicaciones {

	private List<Ubicacion> ubicacionesPersonas;
	private Map<Integer, Ubicacion> mapUbicacionPersona;
	@Autowired
	private UbicacionController ubicacionController = new UbicacionController();
	@Autowired
	private ControladorIncidencias controladorIncidencias = new ControladorIncidencias();
	@Autowired
	private ControladorDistancias controladorDistancias;
	//NEW ARERIBA
	
	public void iniciar() {
		// TRAIGO LAS UBICACIONES QUE ESTAN EN LA DB
		this.ubicacionesPersonas = this.ubicacionController.listar();

	}

//	@Scheduled(fixedRate = 15000)
	public void actualizarUbicaciones() {
		System.out.println("REVISO LAS UBICACIONES A LAS: "+new Date());
		// ACTUALIZAR LAS ubicacionesPersonas
		ubicacionesPersonas = ubicacionController.listar();
		// Convertir de LIST a MAP las ubicaciones
		mapUbicacionPersona = ubicacionesPersonas.stream().collect(Collectors.toMap(x -> x.getIdPersona(), x -> x));
		// NOTIFICAR QUE LAS UBICACIONES SE ACTUALIZARON A LOS OBSERVERS
		notificar(mapUbicacionPersona);
		// CONTROLAR PERDIDA DE LOCALIZACIÓN PARA VERIFICAR SI SE GENERA INCIDENCIAS
		controlarPerdidaLocalizacion();

	}

	public void controlarPerdidaLocalizacion() {
		// TENGO LA FECHA ACTUAL Y LE RESTO 10 MINUTOS PARA VER CUALES ESTAN
		// ILOCALIZABLES
		Date ahora = new Date();
		ahora.setTime(ahora.getTime() - 600000);
		// CREO EL TIMESTAMP PARA OBTENER LAS QUE SEAN ANTERIORES A ESE TIMESTAMP Y
		// CONTROLO
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		controladorIncidencias.controlarIlocalizables(ahoraStamp);
	}

	public void notificar(Map<Integer, Ubicacion> ubicaciones) {
		this.controladorDistancias.calcularDistancias(ubicaciones);
	}

}
