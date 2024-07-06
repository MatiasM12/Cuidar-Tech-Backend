package vdg.repository;


import java.util.List;
import org.springframework.data.repository.Repository;
import vdg.model.domain.ConfigMensaje;


public interface ConfigMensajeRepository extends Repository<ConfigMensaje, Integer> {
	
	public ConfigMensaje save(ConfigMensaje  mensaje);
	
	public void delete(ConfigMensaje  mensaje);
	
	public ConfigMensaje  findById(int id);
	
	public ConfigMensaje  findByTipo(String Tipo);

	public List<ConfigMensaje > findAll();
}
