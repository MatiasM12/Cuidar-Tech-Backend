package vdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import vdg.model.domain.Localidad;

public interface LocalidadRepository extends Repository<Localidad, Integer>{
	
	public List<Localidad> findAll();
	public Localidad save(Localidad localidad);
	public void delete(Localidad localidad);
	@Query(value = "SELECT * FROM Localidad i WHERE i.idProvincia=?1 ", nativeQuery = true)
	public List<Localidad> findByIdProvinciaOrderByNombreAsc(int idProvincia);
	public Localidad findByIdLocalidad(int idLocalidad);
}
