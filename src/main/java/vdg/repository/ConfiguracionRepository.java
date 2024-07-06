package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;
import vdg.model.domain.ConfiguracionLSTM;

public interface ConfiguracionRepository extends Repository<ConfiguracionLSTM, Integer>{
	
	public ConfiguracionLSTM findByIdPersona(int idDamnificada);
	public List<ConfiguracionLSTM> findAll();
	public void save(ConfiguracionLSTM config);
	public void delete(ConfiguracionLSTM config);
}
