package vdg.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import vdg.model.domain.CoordenadasPersona;
import vdg.model.domain.Persona;

public interface CoordenadasPersonaRepository extends Repository<CoordenadasPersona, Integer>{
	
	@Query(value = "SELECT * FROM coordenadasPersona WHERE idPersona = ? LIMIT 27", nativeQuery = true)
	public List<CoordenadasPersona> findAllByidPersona(Persona idPersona);
	public CoordenadasPersona save(CoordenadasPersona coordenada);

}
