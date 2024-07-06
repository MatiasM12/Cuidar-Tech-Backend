package vdg.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import vdg.model.domain.Persona;
import vdg.model.domain.UbicacionesEntrenamiento;


public interface UbicacionEntrenamientoRepository extends Repository<UbicacionesEntrenamiento, Integer>{
	
	public List<UbicacionesEntrenamiento> findAllByIdPersona(Persona idPersona);
	public void save(UbicacionesEntrenamiento ubicacion);
	public void delete(UbicacionesEntrenamiento ubicacion);
	@Transactional
	public void deleteByidPersona(Persona idPersona);
}
