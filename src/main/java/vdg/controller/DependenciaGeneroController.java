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


import vdg.model.domain.DependenciaGenero;
import vdg.repository.DependenciaRepository;

@RestController
@RequestMapping("/dependenciaGenero")
@CrossOrigin
public class DependenciaGeneroController {
	
	
	@Autowired
	private DependenciaRepository dependencia;
	
	
	@GetMapping("/")
	public List <DependenciaGenero> obtenerDependencias(){
		return this.dependencia.findAll();
	}
	
	
	@GetMapping("/{nombreDependencia}")
	public DependenciaGenero dependenciaPorNombre(@PathVariable("nombreDependencia") String nombreDependencia){
		return this.dependencia.findBynombre(nombreDependencia);
	}
	
	
	@PostMapping("/agregarDependencia/")
	public void agregarDependencia(@RequestBody DependenciaGenero agregarDependencia){
		this.dependencia.save(agregarDependencia);
	}
	
	
	@GetMapping("/{municipio}")
	public DependenciaGenero dependenciaPorMunicipio(@PathVariable("municipio") String nombreDependencia){
		return this.dependencia.findBynombre(nombreDependencia);
	}
	
	
	@DeleteMapping("/eliminar")
	public void eliminarDependencia(@RequestBody DependenciaGenero dependencia){
		this.dependencia.delete(dependencia);
	}
	
	
	
	
	
	
	
	

}
