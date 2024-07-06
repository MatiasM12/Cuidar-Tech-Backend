package vdg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vdg.model.domain.RPLugar;
import vdg.model.domain.RPLugarDTO;
import vdg.repository.LocalidadRepository;
import vdg.repository.ProvinciaRepository;
import vdg.repository.RPLugarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/LugaresRestringidos")
public class RPLugarController {

    @Autowired
    private RPLugarRepository lugarRepository;
    @Autowired
    private LocalidadRepository localidadRepository;
    @Autowired
    private ProvinciaRepository provinciaRepository;

    @GetMapping
    public ResponseEntity<List<RPLugar>> obtenerTodosLosLugares() {
        List<RPLugar> lugares = lugarRepository.findAll();
        return new ResponseEntity<>(lugares, HttpStatus.OK);
    }
    
    @GetMapping("/RplugarDto")
    public ResponseEntity<List<RPLugarDTO>> obtenerTodosLosLugaresDto() {
        List<RPLugar> lugares = lugarRepository.findAll();

        // Mapear RPLugar a RPLugarDTO
        List<RPLugarDTO> lugaresDTO = lugares.stream()
            .map(lugar -> new RPLugarDTO(lugar,
            		provinciaRepository.findByIdProvincia(localidadRepository.findByIdLocalidad(lugar.getDireccion().getIdLocalidad()).getIdProvincia()),
            		localidadRepository.findByIdLocalidad(lugar.getDireccion().getIdLocalidad())))
            		.collect(Collectors.toList());

        return new ResponseEntity<>(lugaresDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RPLugar> obtenerLugarPorId(@PathVariable("id") Integer id) {
        Optional<RPLugar> lugarOptional = lugarRepository.findById(id);
        if (lugarOptional.isPresent()) {
            return new ResponseEntity<>(lugarOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/Restriccion/{id}") 
    public ResponseEntity<List<RPLugar>> obtenerRPLugarPorIdRestriccion(@PathVariable int id) {
    	List<RPLugar> lugares = lugarRepository.findRPLugarByIdRestriccion(id);
    	return new ResponseEntity<>(lugares, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RPLugar> crearNuevoLugar(@RequestBody RPLugar lugar) {
        RPLugar nuevoLugar = lugarRepository.save(lugar);
        return new ResponseEntity<>(nuevoLugar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RPLugar> actualizarLugar(@PathVariable Integer id, @RequestBody RPLugar lugar) {
        Optional<RPLugar> lugarOptional = lugarRepository.findById(id);
        	System.out.println(lugarOptional.isPresent());
        
        if (lugarOptional.isPresent()) {
            lugar.setIdRestriccion(id);
            RPLugar lugarActualizado = lugarRepository.save(lugar);
            return new ResponseEntity<>(lugarActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLugar(@PathVariable Integer id) {
        Optional<RPLugar> lugarOptional = lugarRepository.findById(id);
        if (lugarOptional.isPresent()) {
            lugarRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

