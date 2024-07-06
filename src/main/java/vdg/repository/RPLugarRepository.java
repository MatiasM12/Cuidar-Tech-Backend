package vdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vdg.model.domain.RPLugar;


public interface RPLugarRepository extends JpaRepository<RPLugar, Integer> {

	public RPLugar findById(int id);
	public void deleteById(int id);
	@Query("SELECT lugar FROM RPLugar lugar WHERE lugar.idRestriccion = :id")
	public List<RPLugar> findRPLugarByIdRestriccion(int id);


}
