package org.tottus.ventaonline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MobileRequest {

	@JsonProperty("idmovil")
	private String idmovil;
	
	public String getIdmovil() {
		return idmovil;
	}
	public void setIdmovil(String idmovil) {
		this.idmovil = idmovil;
	}
	
}
