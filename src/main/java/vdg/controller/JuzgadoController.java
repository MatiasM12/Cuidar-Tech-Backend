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

import vdg.model.domain.ConfigMensaje;
import vdg.model.domain.Juzgado;
import vdg.repository.JuzgadoRepository;


@RestController
@RequestMapping("/Juzgado")
@CrossOrigin
public class JuzgadoController {
	
	@Autowired
	JuzgadoRepository juzgadoRepository;
	
    @PostMapping
    public Juzgado agregar(@RequestBody Juzgado juzgado) {
        return juzgadoRepository.save(juzgado);
    }
	
    @GetMapping
    public List<Juzgado> listar() {
        return juzgadoRepository.findAll();
    }
	
	@PutMapping
	public Juzgado modificar(@RequestBody Juzgado juzgado) {
		
		return juzgadoRepository.save(juzgado);
	}
	
	@GetMapping("/{id}")
    public Juzgado obtenerPorId(@PathVariable("id") int id) {
        return  juzgadoRepository.findByidJuzgado(id);
    }
	
	@GetMapping("/{cuidad}")
    public Juzgado obtenerPorCiudad(@PathVariable("ciudad") String ciudad) {
        return  juzgadoRepository.findByCiudad(ciudad);
    }
	
	@GetMapping("/{nombre}")
    public Juzgado obtenerPorNombre(@PathVariable("nombre") String nombre) {
        return  juzgadoRepository.findBynombre(nombre);
    }
	
    @DeleteMapping("/{id}")
    public void borrar(@PathVariable("id") int id) {
    	Juzgado configMensaje = juzgadoRepository.findByidJuzgado(id);
        if (configMensaje != null) {
        	juzgadoRepository.delete(configMensaje);
        }
    }
}
