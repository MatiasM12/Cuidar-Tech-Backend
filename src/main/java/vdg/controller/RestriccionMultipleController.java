package vdg.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.RPLugar;
import vdg.model.domain.RPLugarDTO;
import vdg.model.domain.RestriccionMultiple;
import vdg.model.domain.RestriccionMultipleDTO;
import vdg.repository.LocalidadRepository;
import vdg.repository.PersonaRepository;
import vdg.repository.ProvinciaRepository;
import vdg.repository.RPLugarRepository;
import vdg.repository.RestriccionMultipleRepository;
import vdg.repository.RestriccionPerimetralRepository;


@RestController
@RequestMapping("/RestriccionMultiple")
@CrossOrigin
public class RestriccionMultipleController {
	
	  	@Autowired
	    private RestriccionMultipleRepository resMulRepo;
	    @Autowired
	    private LocalidadRepository localidadRepository;
	    @Autowired
	    private ProvinciaRepository provinciaRepository;
	    @Autowired
	    private PersonaRepository personaRepo;

	    /*
	     * Obtiene todas las restricciones multiple de la base de datos
	     * @author Nicolas 
	     * **/
	    @GetMapping
	    public ResponseEntity<List<RestriccionMultiple>> obtenerRestriccionesMultiples() {
	        List<RestriccionMultiple> lugares = resMulRepo.findAll();
	        return new ResponseEntity<>(lugares, HttpStatus.OK);
	    }
	    
	    
	    @DeleteMapping("/eliminarRestriccion/{idRestriccionMultiple}")
	    public ResponseEntity<Void> eliminarRestriccion(@PathVariable("idRestriccionMultiple") int idRestriccionMultiple) {
	    	this.resMulRepo.deleteById(idRestriccionMultiple);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	    
	    @PostMapping("/nuevaRestriccion")
	    public ResponseEntity<RestriccionMultiple> nuevaRestriccion(@RequestBody RestriccionMultiple res) {
	    	this.resMulRepo.save(res);
	        return new ResponseEntity<RestriccionMultiple>(res,HttpStatus.OK);
	    }
	    
	    
	    @GetMapping("/RestriccionMultipleDTO")
	    public ResponseEntity<List<RestriccionMultipleDTO>> obtenerRestriccionesMultiplesDTO() {
	        List<RestriccionMultiple> restricciones = this.resMulRepo.findAll();
	        // Mapear RPLugar a RPLugarDTO
	        List<RestriccionMultipleDTO> restriccionMultiple = restricciones.stream()
	            .map(resMul -> new RestriccionMultipleDTO(resMul,
	            			   this.provinciaRepository.findByIdProvincia(resMul.getIdProvincia()),
	            			   this.localidadRepository.findByIdLocalidad(resMul.getDireccion().getIdLocalidad()),
	            			   this.personaRepo.findById(resMul.getIdPersona())))
	            			   .collect(Collectors.toList());

	        return new ResponseEntity<>(restriccionMultiple,HttpStatus.OK);
	    }
}
