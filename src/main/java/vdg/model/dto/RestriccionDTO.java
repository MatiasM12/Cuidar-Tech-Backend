package vdg.model.dto;

import vdg.model.domain.Grupo;
import vdg.model.domain.Persona;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.Usuario;

public class RestriccionDTO {

	private Persona damnificada;
	private Persona victimario;
	private Usuario administrativo;
	private RestriccionPerimetral restriccion;
	private Grupo grupo;

	public RestriccionDTO(Persona damnificada, Persona victimario, Usuario administrativo, RestriccionPerimetral restriccion, Grupo grupo) {
		this.damnificada = damnificada;
		this.victimario = victimario;
		this.administrativo = administrativo;
		this.restriccion = restriccion;
		this.grupo =grupo;
	}

	public Persona getDamnificada() {
		return damnificada;
	}

	public void setVictima(Persona damnificada) {
		this.damnificada = damnificada;
	}

	public Persona getVictimario() {
		return victimario;
	}

	public void setVictimario(Persona victimario) {
		this.victimario = victimario;
	}

	public Usuario getAdministrativo() {
		return administrativo;
	}

	public void setAdministrativo(Usuario administrativo) {
		this.administrativo = administrativo;
	}

	public RestriccionPerimetral getRestriccion() {
		return restriccion;
	}

	public void setRestriccion(RestriccionPerimetral restriccion) {
		this.restriccion = restriccion;
	}

	public void setDamnificada(Persona damnificada) {
		this.damnificada = damnificada;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	
}
