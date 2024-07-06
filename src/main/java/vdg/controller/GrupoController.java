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

import vdg.model.domain.Grupo;
import vdg.repository.GrupoRepository;

@RestController
@RequestMapping("/grupo")
@CrossOrigin
public class GrupoController {
	
	@Autowired
	public GrupoRepository repository;
	
	@PostMapping("/nuevoGrupo")
	/**
	 * Agrega un nuevo grupo de trabajo a la base de datos con el grupo pasado por parametro
	 * **/
	public void nuevoGrupo(@RequestBody Grupo grupo) {
		//System.out.println(grupo.getIdUsuario());
		this.repository.save(grupo);
	}
	/**
	 * Elimina el  grupo pasado por parametro de la base de datos 
	 * **/
	@DeleteMapping("/eliminarGrupo")
	public void eliminarGrupo(@RequestBody Grupo grupo) {
		this.repository.delete(grupo);
	}
	/**
	 * Obtiene todos los grupos de trabajo
	 * **/
	@GetMapping("/obtenerGrupos")
	public List<Grupo> obtenerGrupos() {
		return this.repository.findAll();
	}
	
	/**
	 * Obtiene todos los grupos de trabajo que coincidan con el id
	 * **/
	@GetMapping("/obtenerGruposPorId/{idGrupo}")
	public List<Grupo> obtenerGruposPorId(@PathVariable("idGrupo") int idGrupo) {
		return this.repository.findAllByidGrupo(idGrupo);
	}
	
	/**
	 * Obtiene los grupos de trabajo que coincidan con el id
	 * **/
	@GetMapping("/obtenerGrupo/{idGrupo}")
	public Grupo obtenerGrupo(@PathVariable("idGrupo") int idGrupo) {
		return this.repository.findDistinctByidGrupo(idGrupo);
	}
	
	
	
	/**
	 * Obtiene los grupos de trabajo que coincidan con el string pasado por parametro
	 * **/
	@GetMapping("/obtenerGrupoPorNombre/{nombreGrupo}")
	public Grupo obtenerGrupo(@PathVariable("nombreGrupo") String nombreGrupo) {
		return this.repository.findBynombreGrupo(nombreGrupo);
	}
	

}
