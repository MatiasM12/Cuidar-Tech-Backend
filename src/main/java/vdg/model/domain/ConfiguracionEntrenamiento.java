package vdg.model.domain;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

public class ConfiguracionEntrenamiento {
	
	ConfiguracionLSTM config;
	List<UbicacionesEntrenamiento> ubicaciones;
	public ConfiguracionLSTM getConfig() {
		return config;
	}
	public void setConfig(ConfiguracionLSTM config) {
		this.config = config;
	}
	public List<UbicacionesEntrenamiento> getUbicaciones() {
		return ubicaciones;
	}
	public void setUbicaciones(List<UbicacionesEntrenamiento> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	
	

}
