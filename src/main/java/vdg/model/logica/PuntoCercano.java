package vdg.model.logica;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vdg.model.domain.Comisaria;

@Component
public class PuntoCercano {
	

	
	public Comisaria puntoMasCercano(BigDecimal lat1, BigDecimal lon1, List<Comisaria> comisarias) throws Exception {
		
		if(comisarias.size()==0) throw new Exception ("No existen comisarias cercanas al punto, ya que no se proporcionaron comisarias");
		
		double distanciaMasCercana = CalculadorDistancias.obtenerDistancia(lat1,lon1, new BigDecimal(comisarias.get(0).getCoordenadaX()) , new BigDecimal(comisarias.get(0).getCoordenadaY()));
		Comisaria comisariaMasCercana = comisarias.get(0);
		
		for(Comisaria comisaria : comisarias) {
			double nuevaDistanciaCercana = CalculadorDistancias.obtenerDistancia(lat1,lon1, new BigDecimal(comisaria.getCoordenadaX()) , new BigDecimal(comisaria.getCoordenadaY()));
			if (distanciaMasCercana > nuevaDistanciaCercana) {
				distanciaMasCercana = nuevaDistanciaCercana;
				comisariaMasCercana = comisaria;
			}
		
		}
		return comisariaMasCercana;
		
		
		
	
	}
	

}
