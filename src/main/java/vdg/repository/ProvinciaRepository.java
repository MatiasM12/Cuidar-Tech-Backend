package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.Provincia;

public interface ProvinciaRepository extends Repository<Provincia, Integer> {
	
	public List<Provincia> findAll();
	public Provincia findByIdProvincia(int idProvincia);
}
