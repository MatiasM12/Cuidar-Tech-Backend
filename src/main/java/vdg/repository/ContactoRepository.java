package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.Contacto;

public interface ContactoRepository extends Repository<Contacto, Integer> {
	
	public List<Contacto> findByIdDamnificada(int idDamnificada);
	public Contacto save(Contacto contacto);
	public void delete(Contacto contacto);
	
}
