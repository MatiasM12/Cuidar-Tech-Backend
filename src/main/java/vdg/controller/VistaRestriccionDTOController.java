package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.VistaRestriccionDTO;
import vdg.repository.VistaRestriccionDTORepository;

@RestController
@RequestMapping("/vistaRestriccionDTO")
@CrossOrigin
public class VistaRestriccionDTOController {
	
	@Autowired
	private VistaRestriccionDTORepository vistaRestriccionDTOrepo;
	
	@GetMapping("{idAdministrativo}")
	public List<VistaRestriccionDTO> getRestriccionesAdministrativo(@PathVariable("idAdministrativo") int idAdministrativo){
		return vistaRestriccionDTOrepo.findByIdAdministrativo(idAdministrativo);
	};

	@GetMapping("/getByIdGrupo/{idGrupo}")
	public List<VistaRestriccionDTO> getRestriccionesGrupo(@PathVariable("idGrupo") int idGrupo){
		return vistaRestriccionDTOrepo.findByIdGrupo(idGrupo);
	};
	
	
}
