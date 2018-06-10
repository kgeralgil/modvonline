package org.tottus.ventaonline.model;

public class MobileResponse {

	private int code;
	private String message;
	private Descuento data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Descuento getData() {
		return data;
	}
	public void setData(Descuento data) {
		this.data = data;
	}
	
}
