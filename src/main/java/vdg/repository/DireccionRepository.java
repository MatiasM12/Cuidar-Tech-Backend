package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.Direccion;

public interface DireccionRepository extends Repository<Direccion, Integer>{
	
	public List<Direccion> findAll();
	public Direccion save(Direccion direccion);
	public void delete(Direccion direccion);
	public Direccion findByIdDireccion(int idDireccion);
}
