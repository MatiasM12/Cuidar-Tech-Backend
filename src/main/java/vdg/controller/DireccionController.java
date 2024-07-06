package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.Direccion;
import vdg.repository.DireccionRepository;

@RestController
@RequestMapping("/Direccion")
@CrossOrigin
public class DireccionController {
	
	@Autowired
	private DireccionRepository direccionRepo;
		
	@GetMapping
	public List<Direccion> listar(){
		return direccionRepo.findAll();
	}
	
	@PostMapping
	public Direccion agregar(@RequestBody Direccion direccion){
		return direccionRepo.save(direccion);
	}
	
	@DeleteMapping("/{id}")
	public void borrar(@PathVariable("id") int id) {
		Direccion d = new Direccion();
		d.setIdDireccion(id);
		direccionRepo.delete(d);
	}
	
	public int getId(Direccion direccion) {
		List<Direccion> direcciones = listar();
		for(Direccion dir : direcciones) {
			if(dir.equals(direccion))
				return dir.getIdDireccion();
		}
		
		return -1;
	}
	
	public Direccion findByIdDireccion(int idDireccion) {
		return direccionRepo.findByIdDireccion(idDireccion);
	}
	
	@PutMapping
	public Direccion modificarDireccion(Direccion direccion) {
		return direccionRepo.save(direccion);
	}


}
