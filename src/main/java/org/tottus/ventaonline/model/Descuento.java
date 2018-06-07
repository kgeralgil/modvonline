package org.tottus.ventaonline.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Descuento {

	private int idProducto; 
	private String descripcion; 
	private String marca; 
	private Double precioUnitario;
	private Double porcentajeDescuento;
	private int restriccionCantidad;
	private String imagen;
	private Date fechaCaducidad;
	private String codDescuento;
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Double getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	public void setPorcentajeDescuento(Double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	public int getRestriccionCantidad() {
		return restriccionCantidad;
	}
	public void setRestriccionCantidad(int restriccionCantidad) {
		this.restriccionCantidad = restriccionCantidad;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	} 
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public void calcularFechaCaducidad(){
		LocalDate localDate = LocalDate.now().plusDays(this.restriccionCantidad);
		setFechaCaducidad(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}
	public String getCodDescuento() {
		return codDescuento;
	}
	public void setCodDescuento(String codDescuento) {
		String zeroPad = String.format("%03d", Integer.parseInt(codDescuento));
		this.codDescuento = "DESC" + zeroPad;
	}
	
}
