package vdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import vdg.model.domain.Incidencia;
import vdg.model.domain.RestriccionPerimetral;

public interface IncidenciaRepository extends Repository<Incidencia, Integer> {

	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=?1 ORDER BY i.fecha DESC LIMIT ?2", nativeQuery = true)
	public List<Incidencia> findByIdRestriccionOrderByFechaDesc(int idRestriccion, int limit);

	public Incidencia save(Incidencia incidencia);

	public void delete(Incidencia incidencia);
	
	public List<Incidencia> findAll();

	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=? and (i.topico='VictimarioIlocalizable' "
			+ "or i.topico='DamnificadaIlocalizable') ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getIlocalizable(int idRestriccion);

	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=? and i.topico='DamnificadaIlocalizable' ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getDamnificadaIlocalizable(int idRestriccion);

	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=? and i.topico='VictimarioIlocalizable' ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getVictimarioIlocalizable(int idRestriccion);
	
	
	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=? and i.topico='PruebaDeVidaFallida' ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getPruebaDeVidaFallida(int idRestriccion);
	
	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=? and i.topico='InfraccionDeRestriccion' ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getInfraccionDeRestriccion(int idRestriccion);
	
	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=? and i.topico='FueraDeRutina' ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getFueraDeRutina(int idRestriccion);
	
	
	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=? and i.peligrosidad='ALTA' ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getPeligrosidadA(int idRestriccion);
	
	
	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=? and i.peligrosidad='MEDIA' ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getPeligrosidadM(int idRestriccion);
	
	
	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=? and i.peligrosidad='BAJA' ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getPeligrosidadB(int idRestriccion);
	
	

	public Incidencia findByIdIncidencia(int idIncidencia);

	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=?1 ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getUltimaIncidencia(int idRestriccion);
	
	
}
