package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.DependenciaGenero;


public interface DependenciaRepository extends Repository<DependenciaGenero, Integer>{
	
	public List<DependenciaGenero> findAll();
	
	public DependenciaGenero save(DependenciaGenero dependenciaGenero);
	
	public void delete(DependenciaGenero dependenciaGenero);
	
	public DependenciaGenero findByidDependencia(int idDependenciaGenero);
	
	public DependenciaGenero findBynombre(String nombre);
	
	public DependenciaGenero findBymunicipio(String municipio);
	
	public List<DependenciaGenero> findAllByProvincia(String provincia);
	
	
	public List<DependenciaGenero> findAllBytelefono(String telefono);
	
	
	
	
	
	
	
}
