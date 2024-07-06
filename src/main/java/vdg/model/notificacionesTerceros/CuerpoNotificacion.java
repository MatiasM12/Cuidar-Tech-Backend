package vdg.model.notificacionesTerceros;

public class CuerpoNotificacion {
	
	public String messaging_product;
	public Boolean preview_url;
    public String recipient_type; 
	public String to;
	public CuerpoMensaje text;
	
	
	
	
	
	public Boolean getPreview_url() {
		return preview_url;
	}

	public void setPreview_url(Boolean preview_url2) {
		this.preview_url = preview_url2;
	}

	public String getRecipient_type() {
		return recipient_type;
	}

	public void setRecipient_type(String recipient_type) {
		this.recipient_type = recipient_type;
	}

	public CuerpoMensaje getText() {
		return text;
	}

	public void setText(CuerpoMensaje text) {
		this.text = text;
	}

	public String getMessaging_product() {
		return messaging_product;
	}

	public void setMessaging_product(String messaging_product) {
		this.messaging_product = messaging_product;
	}

	public String getTo() {
		return to;
	}
	
	public void setTo(String telefono) throws Exception {
		if(telefono.length()!=12) throw new Exception("El numero ingresado no tiene 12 digitos");
			this.to = telefono;
	}
	
	
	public CuerpoMensaje CuerpoMensaje() {
		return this.text;
	}
	public void setCuerpoMensaje(CuerpoMensaje cuerpoMensaje) throws Exception {
		this.text = cuerpoMensaje;
	}
}
