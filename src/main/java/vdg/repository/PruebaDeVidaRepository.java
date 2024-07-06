package vdg.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import vdg.model.domain.EstadoPruebaDeVida;
import vdg.model.domain.PruebaDeVida;

public interface PruebaDeVidaRepository extends Repository<PruebaDeVida, Integer>{

	public List<PruebaDeVida> findAll();
	public List<PruebaDeVida> findByIdRestriccion(int idRestriccion);
	public List<PruebaDeVida> findByIdPersonaRestriccionAndEstadoOrderByFechaDesc(int idPersonaRestriccion, EstadoPruebaDeVida estado);
	public List<PruebaDeVida> findByIdPersonaRestriccionOrderByFechaDesc(int idPersonaRestriccion);
	@Query("SELECT p FROM PruebaDeVida p WHERE p.idPersonaRestriccion = :idPersonaRestriccion AND p.esMultiple = false AND tiempoDeRespuesta > current_timestamp()")
	List<PruebaDeVida> findByIdPersonaRestriccionAndEsMultipleFalse(@Param("idPersonaRestriccion") int idPersonaRestriccion);
	public PruebaDeVida save(PruebaDeVida pruebaDeVida);
	@Modifying
	@Transactional
	@Query("UPDATE PruebaDeVida p SET p.estado = :estado WHERE p.idPruebaDeVida = :idPruebaDeVida")
	int updateEstado(@Param("idPruebaDeVida") int idPruebaDeVida, @Param("estado") EstadoPruebaDeVida estado);
	@Query("SELECT p FROM PruebaDeVida p WHERE p.idPruebaDeVidaMultiple = :idPruebaDeVidaMultiple")
	public List<PruebaDeVida> findByIdPruebaDeVidaMultiple(@Param("idPruebaDeVidaMultiple") long idPruebaDeVidaMultiple);
    @Modifying
    @Transactional
    @Query("UPDATE PruebaDeVida p SET p.estado = 'SinRespuesta' WHERE p.idPruebaDeVida = :idPruebaDeVida AND p.tiempoDeRespuesta <= current_timestamp()")
    void updateEstadoASinRespuesta(@Param("idPruebaDeVida") int idPruebaDeVida);

	
}
