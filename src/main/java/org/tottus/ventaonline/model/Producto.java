package org.tottus.ventaonline.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProducto;
	private String codigoProducto;
	private String descripcion;
	private double precioUnitario;
	private String imagen;
	private Date fechaVencimiento;
	
	private String tipoDescuento;
	private int restriccionCantidad;
	private double porcentajeDescuento;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	private double precioUnitarioDescuento;
	
	public Producto() {
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

	public int getRestriccionCantidad() {
		return restriccionCantidad;
	}

	public void setRestriccionCantidad(int restriccionCantidad) {
		this.restriccionCantidad = restriccionCantidad;
	}

	public double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public double getPrecioUnitarioDescuento() {
		return precioUnitarioDescuento;
	}

	public void setPrecioUnitarioDescuento(double precioUnitarioDescuento) {
		this.precioUnitarioDescuento = precioUnitarioDescuento;
	}
	
	

}
