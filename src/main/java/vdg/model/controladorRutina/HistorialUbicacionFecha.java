package vdg.model.controladorRutina;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vdg.controller.UbicacionRutinaController;
import vdg.model.domain.Ubicacion;
import vdg.model.logica.CalculadorDistancias;

@Component
public class HistorialUbicacionFecha {
	
	public List<UbicacionRutina> ubicacionesFecha = new ArrayList<UbicacionRutina>();
	
	@Autowired
	private UbicacionRutinaController ubicacionController = new UbicacionRutinaController();


	public HistorialUbicacionFecha() {
	}

	
	public void cargarUbicaciones(int idPersona, int dia, int hora, int minutos) {
		this.ubicacionesFecha = this.ubicacionController.getUbicacionesPersonaFecha(idPersona, dia, hora, minutos);
		//FALTA FILTRAR POR LA HORA	!!!!!!!!!!!!	
	}
	
	//DEVUELVE UNA UBICACION PROMEDIO SI LA HAY
	public Ubicacion dameUbicacionHabitual(int idPersona, int dia, int hora, int minutos) {

		cargarUbicaciones(idPersona, dia, hora, minutos);
		System.out.println(this.ubicacionesFecha.toString());
		
		List<UbicacionRutina> ubicacionesMasRepetidas = new ArrayList<UbicacionRutina>();
		int maxima = 0;
		//RECORRO LAS UBICACIONES PARA QUEDARME CON LA QUE MAS UBICACIONES CERCANAS TENGA (LA QUE MAS SE REPITE)
		for(UbicacionRutina ubicacion: ubicacionesFecha) {
			List<UbicacionRutina> ubicacionesCercanas = getUbicacionesCercanas(ubicacion);
			if(ubicacionesCercanas.size() >= maxima) {
				ubicacionesMasRepetidas = ubicacionesCercanas;
				maxima = ubicacionesCercanas.size();
			}
		}
		
		System.out.println("Maxima =" + maxima + ".. largo lista = " + ubicacionesMasRepetidas.size());
		
		//CHEQUEO SI LA CANTIDAD DE UBICACIONES QUE QUEDARON ES MAYOR AL 70% Y CALCULO LA UBICACION PROMEDIO PARA RETORNAR
		if(!ubicacionesFecha.isEmpty() && (maxima*100)/ubicacionesFecha.size()>=50) {
			return getUbicacionPromedio(ubicacionesMasRepetidas);
		}
		
		return null;
	}

	//DEVUELVE LA LISTA DE LAS UBICACIONES MAS CERCNAS (AREA DE 100 MTS) A LA UBICACION PASADA
	//Ahora esta en 600 mtrs para probar
	private List<UbicacionRutina> getUbicacionesCercanas(UbicacionRutina ubicacionMedio) {
		
		List<UbicacionRutina> ubicacionesCercanas = new ArrayList<UbicacionRutina>();
				
		for(UbicacionRutina ubicacion: ubicacionesFecha) {
			Double distancia = CalculadorDistancias.obtenerDistancia(ubicacionMedio.getLatitud(), ubicacionMedio.getLongitud(),
					ubicacion.getLatitud(), ubicacion.getLongitud());
			if(distancia <= 600) {
				ubicacionesCercanas.add(ubicacion);
			}
		}
		
		return ubicacionesCercanas;
	}
	
	//CALCULA LA UBICACION PROMEDIO ENTRE TODAS LAS UBICACIONES CERCANAS (AREA DE 100 MTS)
	@SuppressWarnings("deprecation")
	public Ubicacion getUbicacionPromedio(List<UbicacionRutina> ubicaciones) {
		
		Ubicacion ubicacionPromedio = new Ubicacion();
		BigDecimal lat = new BigDecimal(0);
		BigDecimal lon = new BigDecimal(0);
		
		for(UbicacionRutina ubicacion: ubicaciones) {
			System.out.println(ubicacion.getIdUbicacionRutina() + "- lat: " + ubicacion.getLatitud() + "/ lon: " + ubicacion.getLongitud());
			lat = lat.add(ubicacion.getLatitud());
			lon = lon.add(ubicacion.getLongitud());
		}
		
		ubicacionPromedio.setLatitud(lat.divide(new BigDecimal(ubicaciones.size()), 6, BigDecimal.ROUND_HALF_UP));
		ubicacionPromedio.setLongitud(lon.divide(new BigDecimal(ubicaciones.size()), 6, BigDecimal.ROUND_HALF_UP));
		ubicacionPromedio.setIdPersona(ubicaciones.get(0).getIdPersona());
				
		return ubicacionPromedio;
	}
}
