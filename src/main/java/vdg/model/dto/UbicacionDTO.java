package vdg.model.dto;

import vdg.model.domain.Ubicacion;

public class UbicacionDTO {

	private Ubicacion ubicacionDamnificada;
	private Ubicacion ubicacionVictimario;

	public Ubicacion getUbicacionDamnificada() {
		return ubicacionDamnificada;
	}

	public void setUbicacionDamnificada(Ubicacion ubicacionDamnificada) {
		this.ubicacionDamnificada = ubicacionDamnificada;
	}

	public Ubicacion getUbicacionVictimario() {
		return ubicacionVictimario;
	}

	public void setUbicacionVictimario(Ubicacion ubicacionVictimario) {
		this.ubicacionVictimario = ubicacionVictimario;
	}

}
