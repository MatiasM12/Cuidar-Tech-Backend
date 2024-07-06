package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import vdg.model.controladorRutina.ControladorRutina;
import vdg.model.controladorRutina.HistorialUbicacionFecha;
import vdg.model.controladorRutina.UbicacionRutina;
import vdg.model.domain.Ubicacion;
import vdg.repository.UbicacionRutinaRepository;

@RestController
@RequestMapping("/UbicacionRutina")
@CrossOrigin
public class UbicacionRutinaController {

	@Autowired
	private UbicacionRutinaRepository ubicacionRutinaRepo;

	//@Autowired
	//private ControladorRutina controladorRutina;

	@Autowired
	private HistorialUbicacionFecha historial;

	@GetMapping
	public List<UbicacionRutina> listar() {
		/*
		 * Date ahora = new Date(); ahora.setTime(ahora.getTime()); //6 CAE DOMINGO
		 * ahora.setDate(6); ahora.setHours(6); Timestamp ahoraStamp = new
		 * Timestamp(ahora.getTime()); BigDecimal lat = new BigDecimal(-34.586305);
		 * BigDecimal lon = new BigDecimal(-58.644667); Ubicacion ubicacion = new
		 * Ubicacion(); ubicacion.setIdPersona(1); ubicacion.setFecha(ahoraStamp);
		 * ubicacion.setLatitud(lat); ubicacion.setLongitud(lon);
		 * System.out.println("--------------------");
		 * System.out.println("Está en rutina? : " +
		 * controladorRutina.estaEnRutina(ubicacion));
		 * System.out.println("--------------------");
		 */
		return ubicacionRutinaRepo.findAll();
	}

	@GetMapping("/persona={idPersona}/dia={dia}/hora={hora}/minutos={minutos}")
	public Ubicacion ubicacionRutinaria2(@PathVariable("idPersona") int idPersona, @PathVariable("dia") int dia,
			@PathVariable("hora") int hora, @PathVariable("minutos") int minutos) {
		return historial.dameUbicacionHabitual(idPersona, dia, hora, minutos);
	}

	public List<UbicacionRutina> getUbicacionesPersonaFecha(int idPersona, int dia, int hora, int minutos) {

		for (UbicacionRutina ubicacion : ubicacionRutinaRepo.findByPersonaAndFecha(idPersona, dia, hora, minutos)) {
			System.out.println(ubicacion.getFecha());
		}

		return ubicacionRutinaRepo.findByPersonaAndFecha(idPersona, dia, hora, minutos);
	}

	@SuppressWarnings("deprecation")
	@PostMapping
	public UbicacionRutina agregar(@RequestBody UbicacionRutina ubicacionRutina) {
		
		//SI ES RUTINA DE HORA EN PUNTO, SET MINUTOS EN 0, SINO SET MINUTOS EN 30
		if((ubicacionRutina.getFecha().getMinutes()>= 00 && ubicacionRutina.getFecha().getMinutes()<= 05)) {
			ubicacionRutina.getFecha().setMinutes(0);
		}
		else {
			ubicacionRutina.getFecha().setMinutes(30);
		}
		// DEJO SOLO LA HORA Y MINUTOS
		ubicacionRutina.getFecha().setSeconds(0);
		ubicacionRutina.getFecha().setNanos(0);

		return ubicacionRutinaRepo.save(ubicacionRutina);
	}

}
