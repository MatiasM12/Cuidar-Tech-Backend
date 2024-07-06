package vdg.model.dto;

public class ErrorDTO {

	private boolean hayError;
	private String mensajeError;
	
	public ErrorDTO() {
		this.hayError = false;
		this.mensajeError = "";
	}

	public boolean getHayError() {
		return hayError;
	}

	public void setHayError() {
		this.hayError = true;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void addMensajeError(String mensajeError) {
		this.mensajeError += "\n"+mensajeError;
	}
	
}
