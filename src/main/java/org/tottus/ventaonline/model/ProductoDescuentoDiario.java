package org.tottus.ventaonline.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "productodescuentodiario")
public class ProductoDescuentoDiario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProductoDescuentoDiario;
	private int idProducto;
	private int cantidadDisponible;
	private String tipoDescuento;
	private String estado;
	private Double porcentajeDescuento;
	private int restriccionCantidad;
	private Date fechaIniVigencia;
	private Date fechaFinVigencia;
	private int usuarioCreacion;
	private Date fechaCreacion;
	private int usuarioModificacion;
	private Date fechaModificacion;
	
	public Date getFechaIniVigencia() {
		return fechaIniVigencia;
	}

	public void setFechaIniVigencia(Date fechaIniVigencia) {
		this.fechaIniVigencia = fechaIniVigencia;
	}

	public Date getFechaFinVigencia() {
		return fechaFinVigencia;
	}

	public void setFechaFinVigencia(Date fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}

	public int getIdProductoDescuentoDiario() {
		return idProductoDescuentoDiario;
	}

	public void setIdProductoDescuentoDiario(int idProductoDescuentoDiario) {
		this.idProductoDescuentoDiario = idProductoDescuentoDiario;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public String getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public int getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(int usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(int usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public ProductoDescuentoDiario() {
		
	}
	

}
