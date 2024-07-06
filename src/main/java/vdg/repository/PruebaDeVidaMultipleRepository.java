package vdg.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vdg.model.domain.PruebaDeVidaMultiple;

@Repository
public interface PruebaDeVidaMultipleRepository extends JpaRepository<PruebaDeVidaMultiple, Long> {

	@Query("SELECT p FROM PruebaDeVidaMultiple p WHERE p.idPersona = :idPersona")
	public Collection<PruebaDeVidaMultiple> findAllByIdPersona(@Param("idPersona") Long idPersona);
	@Query("SELECT p FROM PruebaDeVidaMultiple p WHERE p.idPersona = :idPersona AND p.tiempoDeRespuesta > current_timestamp()")
	public Collection<PruebaDeVidaMultiple> findAllByIdPersonaApp(@Param("idPersona") Long idPersona);
    @Modifying
    @Transactional
    @Query("UPDATE PruebaDeVidaMultiple p SET p.estado = 'SinRespuesta' WHERE p.idPruebaDeVidaMultiple = :idPruebaDeVidaMultiple AND p.tiempoDeRespuesta <= current_timestamp()")
	public void updateEstadoASinRespuesta(Long idPruebaDeVidaMultiple);

}