package vdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vdg.model.domain.ConfigMensaje;
import vdg.model.domain.DetalleRestriccion;
import vdg.model.domain.RPLugar;
import vdg.model.domain.RestriccionPerimetral;
import vdg.repository.DetalleRestriccionRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/DetalleRestricciones")
public class DetalleRestriccionController {

    @Autowired
    private DetalleRestriccionRepository detalleRepository;

    // Crear nueva DetalleRestriccion
    @PostMapping("/Agregar")
    public ResponseEntity<DetalleRestriccion> crearDetalleRestriccion(@RequestBody DetalleRestriccion detalleRestriccion) {
    	System.out.println(detalleRestriccion);
        try {
        	
            DetalleRestriccion _detalleRestriccion = detalleRepository.save(detalleRestriccion);
            return new ResponseEntity<>(_detalleRestriccion, HttpStatus.CREATED);
        } catch (Exception e) {
        	System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener todas las DetalleRestricciones
    @GetMapping
    public ResponseEntity<List<DetalleRestriccion>> obtenerTodasLasDetalleRestricciones() {
        try {
            List<DetalleRestriccion> detalleRestricciones = detalleRepository.findAll();
            if (detalleRestricciones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(detalleRestricciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener DetalleRestriccion por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleRestriccion> obtenerDetalleRestriccionPorId(@PathVariable("id") Integer id) {
        Optional<DetalleRestriccion> detalleRestriccionData = detalleRepository.findById(id);

        if (detalleRestriccionData.isPresent()) {
            return new ResponseEntity<>(detalleRestriccionData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/TraerRes/{idRestriccion}")
    public DetalleRestriccion getDetalleRestriccionByIdRestriccion(@PathVariable int idRestriccion) {
        return detalleRepository.findByRestriccion_IdRestriccion(idRestriccion);
    }

	@PutMapping("/Update")
	public DetalleRestriccion modificar(@RequestBody DetalleRestriccion mensaje) {
		
		return detalleRepository.save(mensaje);
	}

    // Eliminar DetalleRestriccion por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarDetalleRestriccion(@PathVariable("id") int id) {
        try {
            detalleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    

}

