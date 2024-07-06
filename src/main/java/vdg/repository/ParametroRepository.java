package vdg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import vdg.model.domain.Parametro;

public interface ParametroRepository extends Repository<Parametro, Integer>{

	List<Parametro> findAll();

	Optional<Parametro> findById(int id);

	Parametro save(Parametro parametro);

	void deleteById(int id);

}
