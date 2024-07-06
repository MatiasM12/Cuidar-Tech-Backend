package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.BotonAntipanico;

public interface BotonAntipanicoRepository extends Repository<BotonAntipanico, Integer>{
	
	public List<BotonAntipanico> findByIdDamnificada(int idDamnificada);
	public BotonAntipanico save(BotonAntipanico alerta);
	
}
