package vdg.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import vdg.model.domain.FotoIdentificacion;

public interface FotoIdentificacionRepository extends Repository<FotoIdentificacion, Integer>{
	
	public List<FotoIdentificacion> findAll();
	public FotoIdentificacion findByIdPersona(int idPersona);
	public FotoIdentificacion save(FotoIdentificacion fotoIdentificacion);
	public void delete(FotoIdentificacion fotoIdentificacion);
	@Transactional
	public void deleteByIdPersona(int idPersona);
}
