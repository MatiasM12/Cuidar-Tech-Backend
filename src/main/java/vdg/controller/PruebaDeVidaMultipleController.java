package vdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vdg.model.domain.EstadoPruebaDeVida;
import vdg.model.domain.PruebaDeVidaMultiple;
import vdg.repository.PruebaDeVidaMultipleRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/PruebaDeVidaMultiple")
public class PruebaDeVidaMultipleController {

    @Autowired
    private PruebaDeVidaMultipleRepository pruebaDeVidaMultipleRepository;

    @PostMapping
    public PruebaDeVidaMultiple guardarPruebasDeVidaMultiple(@RequestBody PruebaDeVidaMultiple pruebaDeVidaMultiple) {
          return pruebaDeVidaMultipleRepository.save(pruebaDeVidaMultiple);
    }
    
    @GetMapping("/getAll/{idPersona}")
    public Collection<PruebaDeVidaMultiple> getPruebasDeVidaMultiple(@PathVariable("idPersona") Long idPersona) {
    	this.pasarASinRespuesta((List<PruebaDeVidaMultiple>) pruebaDeVidaMultipleRepository.findAllByIdPersona(idPersona));
        return pruebaDeVidaMultipleRepository.findAllByIdPersona(idPersona);
    }
    
    @GetMapping("/getAllApp/{idPersona}")
    public Collection<PruebaDeVidaMultiple> getPruebasDeVidaMultipleApp(@PathVariable("idPersona") Long idPersona) {
    	this.pasarASinRespuesta((List<PruebaDeVidaMultiple>) pruebaDeVidaMultipleRepository.findAllByIdPersonaApp(idPersona));
    	return pruebaDeVidaMultipleRepository.findAllByIdPersonaApp(idPersona);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPruebaDeVidaMultipleById(@PathVariable("id") Long id) {
        Optional<PruebaDeVidaMultiple> pruebaDeVidaOptional = pruebaDeVidaMultipleRepository.findById(id);
        if (pruebaDeVidaOptional.isPresent()) {
            PruebaDeVidaMultiple pruebaDeVida = pruebaDeVidaOptional.get();
            return ResponseEntity.ok(pruebaDeVida);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPruebaDeVidaMultiple(@PathVariable("id") Long id, @RequestBody PruebaDeVidaMultiple pruebaDeVidaMultiple) {
        try {
            pruebaDeVidaMultiple.setIdPruebaDeVidaMultiple(id);
            PruebaDeVidaMultiple updatedPrueba = pruebaDeVidaMultipleRepository.save(pruebaDeVidaMultiple);
            return ResponseEntity.ok(updatedPrueba);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la prueba de vida múltiple: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPruebaDeVidaMultiple(@PathVariable("id") Long id) {
        try {
            pruebaDeVidaMultipleRepository.deleteById(id);
            return ResponseEntity.ok("Prueba de vida múltiple eliminada correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la prueba de vida múltiple: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para actualizar el estado de una prueba de vida múltiple
    @PutMapping("/estado/{id}")
    public ResponseEntity<?> actualizarEstadoPruebaDeVidaMultiple(@PathVariable("id") Long id, @RequestBody Map<String, EstadoPruebaDeVida> nuevoEstado) {
        try {
            PruebaDeVidaMultiple pruebaDeVidaMultiple = pruebaDeVidaMultipleRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("ID de prueba de vida múltiple no válido: " + id));
            pruebaDeVidaMultiple.setEstado(nuevoEstado.get("estado"));
            PruebaDeVidaMultiple pruebaActualizada = pruebaDeVidaMultipleRepository.save(pruebaDeVidaMultiple);
            return ResponseEntity.ok(pruebaActualizada);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el estado de la prueba de vida múltiple: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	public void pasarASinRespuesta(List<PruebaDeVidaMultiple> pruebasDeVida) {
		for(PruebaDeVidaMultiple prueba: pruebasDeVida) {
			this.pruebaDeVidaMultipleRepository.updateEstadoASinRespuesta(prueba.getIdPruebaDeVidaMultiple());
		}
	}
}
