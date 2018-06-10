package org.tottus.ventaonline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "controlpromocion")
public class ControlPromocion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idControlPromocion;
	private String idEquipoMovil;
	private String codigoProducto;
	private String fechaRegistro;
	private String estado;
	public int getIdControlPromocion() {
		return idControlPromocion;
	}
	public void setIdControlPromocion(int idControlPromocion) {
		this.idControlPromocion = idControlPromocion;
	}
	public String getIdEquipoMovil() {
		return idEquipoMovil;
	}
	public void setIdEquipoMovil(String idEquipoMovil) {
		this.idEquipoMovil = idEquipoMovil;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
