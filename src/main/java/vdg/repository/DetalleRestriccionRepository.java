package vdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vdg.model.domain.DetalleRestriccion;


@Repository
public interface DetalleRestriccionRepository extends JpaRepository<DetalleRestriccion, Integer> {

	public DetalleRestriccion findById(int id);
	public void deleteById(int id);
	DetalleRestriccion findByRestriccion_IdRestriccion(int idRestriccion);
	public void deleteByRestriccion_IdRestriccion(int idRestriccion);
	public DetalleRestriccion save(DetalleRestriccion detalle);
	
	

	public List<DetalleRestriccion> findAll();

}
