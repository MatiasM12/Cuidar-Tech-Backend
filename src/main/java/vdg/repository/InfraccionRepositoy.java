package vdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import vdg.model.domain.Infraccion;

public interface InfraccionRepositoy extends Repository<Infraccion, Integer> {
	
	public List<Infraccion> findAll();
	public List<Infraccion> findByIdRestriccion(int idRestriccion);
	public Infraccion save(Infraccion infraccion);
	@Query(value = "SELECT * FROM Infraccion i WHERE i.idRestriccion=?1 ORDER BY i.fecha DESC LIMIT 1", nativeQuery = true)
	public Infraccion getUltimaInfraccion(int idRestriccion);
	
}
