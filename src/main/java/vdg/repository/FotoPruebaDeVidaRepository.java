package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;
import vdg.model.domain.FotoPruebaDeVida;

public interface FotoPruebaDeVidaRepository extends Repository<FotoPruebaDeVida, Integer>{
	
	public List<FotoPruebaDeVida> findAll();
	public FotoPruebaDeVida findByIdPruebaDeVida(int idPruebaDeVida);
	public FotoPruebaDeVida save(FotoPruebaDeVida fotoPruebaDeVida);
	public void delete(FotoPruebaDeVida fotoPruebaDeVida);
}
