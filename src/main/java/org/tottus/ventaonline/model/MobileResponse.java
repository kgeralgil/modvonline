package org.tottus.ventaonline.model;

public class MobileResponse {

	private int code;
	private String message;
	private Producto data;
	
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
	public Producto getData() {
		return data;
	}
	public void setData(Producto data) {
		this.data = data;
	}
	
}
