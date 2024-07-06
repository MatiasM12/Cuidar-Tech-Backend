package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.Localidad;
import vdg.repository.LocalidadRepository;

@RestController
@RequestMapping("/Localidad")
@CrossOrigin
public class LocalidadController {
	
	@Autowired
	private LocalidadRepository localidadRepo;
		
	@GetMapping
	public List<Localidad> listar(){
		return localidadRepo.findAll();
	}

	@GetMapping("/{id}")
	public List<Localidad> listarLocalidadesPorProvincia(@PathVariable("id") int idProvincia){
		return localidadRepo.findByIdProvinciaOrderByNombreAsc(idProvincia);
	}
	
	@GetMapping("/Buscar/{idLocalidad}")
	public Localidad getLocalidad(@PathVariable("idLocalidad") int idLocalidad) {
		return localidadRepo.findByIdLocalidad(idLocalidad);
	}

	@PostMapping
	public Localidad agregar(@RequestBody Localidad localidad){
		return localidadRepo.save(localidad);
	}
	
	@DeleteMapping("/{id}")
	public void borrar(@PathVariable("id") int id) {
		Localidad l = new Localidad();
		l.setIdLocalidad(id);
		localidadRepo.delete(l);
	}


}
