package vdg.model.logica;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class CalculadorDistancias {

	private static int radioTierra = 6371; // Radio de la tierra

	// Formula de Haversine para obtener distancia entre 2 coordenadas
	public static Double obtenerDistancia(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {
		 Double distanciaLatitudes = convertiraRadianes(lat2.doubleValue()-lat1.doubleValue());
		 Double distanciaLongitudes = convertiraRadianes(lon2.doubleValue()-lon1.doubleValue());
		 Double formulaA = Math.sin(distanciaLatitudes / 2) * Math.sin(distanciaLatitudes / 2) + 
		 Math.cos(convertiraRadianes(lat1.doubleValue())) * Math.cos(convertiraRadianes(lat2.doubleValue())) * 
		 Math.sin(distanciaLongitudes / 2) * Math.sin(distanciaLongitudes / 2);
		 Double formulaC = 2000 * Math.atan2(Math.sqrt(formulaA), Math.sqrt(1-formulaA));
		 Double distance = radioTierra * formulaC;
		 return distance;
	}

	private static Double convertiraRadianes(Double distancia) {
		return distancia * Math.PI / 180;
	}
}
