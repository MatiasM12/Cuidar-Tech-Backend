package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.Provincia;
import vdg.repository.ProvinciaRepository;

@RestController
@RequestMapping("/Provincias")
@CrossOrigin
public class ProvinciaController {
	
	@Autowired
	private ProvinciaRepository provinciaRepo;
	
	@GetMapping
	public List<Provincia> listar() {
		return provinciaRepo.findAll();
	}

}
