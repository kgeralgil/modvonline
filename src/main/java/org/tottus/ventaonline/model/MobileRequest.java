package org.tottus.ventaonline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MobileRequest {

	@JsonProperty("idmovil")
	private String idmovil;
	@JsonProperty("textPhrase")
	private String[] textPhrase;
	
	public String getIdmovil() {
		return idmovil;
	}
	public void setIdmovil(String idmovil) {
		this.idmovil = idmovil;
	}
	public String[] getTextPhrase() {
		return textPhrase;
	}
	public void setTextPhrase(String[] textPhrase) {
		this.textPhrase = textPhrase;
	}
	
}
