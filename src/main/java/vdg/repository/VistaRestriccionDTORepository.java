package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.VistaRestriccionDTO;

public interface VistaRestriccionDTORepository extends Repository<VistaRestriccionDTO, Integer>{
	
	public List<VistaRestriccionDTO> findAll();
	public List<VistaRestriccionDTO> findByIdAdministrativo(int idAdministrativo);
	public List<VistaRestriccionDTO> findByIdGrupo(int idGrupo);
}
