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
import vdg.model.domain.RestriccionPerimetral;
import vdg.repository.ConfigMensajeRepository;

@RestController
@RequestMapping("/configMensaje")
@CrossOrigin
public class ConfigMensajeController {

    @Autowired
    private ConfigMensajeRepository configMensajeRepo;

    @GetMapping
    public List<ConfigMensaje> listar() {
        return configMensajeRepo.findAll();
    }

    @PostMapping
    public ConfigMensaje agregar(@RequestBody ConfigMensaje configMensaje) {
        return configMensajeRepo.save(configMensaje);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable("id") int id) {
        ConfigMensaje configMensaje = configMensajeRepo.findById(id);
        if (configMensaje != null) {
            configMensajeRepo.delete(configMensaje);
        }
    }

    @GetMapping("/{id}")
    public ConfigMensaje obtenerPorId(@PathVariable("id") int id) {
        return configMensajeRepo.findById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public ConfigMensaje obtenerPorTipo(@PathVariable("tipo") String tipo) {
        return configMensajeRepo.findByTipo(tipo);
    }

	@PutMapping
	public ConfigMensaje modificar(@RequestBody ConfigMensaje mensaje) {
		
		return configMensajeRepo.save(mensaje);
	}
}