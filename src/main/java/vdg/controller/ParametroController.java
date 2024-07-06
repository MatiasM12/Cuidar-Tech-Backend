package vdg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vdg.model.domain.Parametro;
import vdg.repository.ParametroRepository;

@RestController
@RequestMapping("/Parametro")
public class ParametroController {

    @Autowired
    private ParametroRepository parametroRepository;

    // Método para obtener todos los parámetros
    @GetMapping
    public ResponseEntity<List<Parametro>> getAllParametros() {
        List<Parametro> parametros = parametroRepository.findAll();
        return new ResponseEntity<>(parametros, HttpStatus.OK);
    }

    // Método para obtener un parámetro por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Parametro> getParametroById(@PathVariable("id") int id) {
        Optional<Parametro> parametro = parametroRepository.findById(id);
        if (parametro.isPresent()) {
            return new ResponseEntity<>(parametro.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Método para crear un nuevo parámetro
    @PostMapping
    public ResponseEntity<Parametro> createParametro(@RequestBody Parametro parametro) {
        Parametro newParametro = parametroRepository.save(parametro);
        return new ResponseEntity<>(newParametro, HttpStatus.CREATED);
    }

    // Método para actualizar un parámetro existente
    @PutMapping("/{id}")
    public ResponseEntity<Parametro> updateParametro(@PathVariable("id") int id, @RequestBody Parametro parametro) {
        Optional<Parametro> existingParametro = parametroRepository.findById(id);
        if (existingParametro.isPresent()) {
            parametro.setIdParametro(id); // Asegura que el ID sea el mismo que el de la entidad existente
            Parametro updatedParametro = parametroRepository.save(parametro);
            return new ResponseEntity<>(updatedParametro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Método para eliminar un parámetro por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParametro(@PathVariable("id") int id) {
        Optional<Parametro> parametro = parametroRepository.findById(id);
        if (parametro.isPresent()) {
            parametroRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

