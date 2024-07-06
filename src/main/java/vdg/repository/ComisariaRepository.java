package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;


import vdg.model.domain.Comisaria;


public interface ComisariaRepository extends Repository<Comisaria, Integer> {
	
	
	public Comisaria save(Comisaria comisaria);
	
	public void delete(Comisaria comisaria);
	
	public Comisaria findByidComisaria(int id);
	
	public List<Comisaria> findAllBypartido(String partido);

	public List<Comisaria> findAll();
	
	
	public Comisaria findBynombre(String nombre);
}
