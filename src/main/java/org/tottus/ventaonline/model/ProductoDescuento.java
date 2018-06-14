package org.tottus.ventaonline.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "productodescuento")
public class ProductoDescuento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProductoDescuento;
	private String codDescuento;
	private int idProducto;
	private String tipoDescuento;
	private String fuente;
	private Double pctDescuento;
	private int cantDisponible;
	private Double pctDescuentoAdic;
	private String estado;
	private int cantDisponibleAdic;
	private int unidadesProdDescuento;
	private int diasVigencia;
	private int diasEnDescuento;
	
	public ProductoDescuento() {
		
	}
	
	public int getIdProductoDescuento() {
		return idProductoDescuento;
	}

	public void setIdProductoDescuento(int idProductoDescuento) {
		this.idProductoDescuento = idProductoDescuento;
	}

	public String getCodDescuento() {
		return codDescuento;
	}

	public void setCodDescuento(String codDescuento) {
		this.codDescuento = codDescuento;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public Double getPctDescuento() {
		return pctDescuento;
	}

	public void setPctDescuento(Double pctDescuento) {
		this.pctDescuento = pctDescuento;
	}

	public int getCantDisponible() {
		return cantDisponible;
	}

	public void setCantDisponible(int cantDisponible) {
		this.cantDisponible = cantDisponible;
	}

	public Double getPctDescuentoAdic() {
		return pctDescuentoAdic;
	}

	public void setPctDescuentoAdic(Double pctDescuentoAdic) {
		this.pctDescuentoAdic = pctDescuentoAdic;
	}

	public int getCantDisponibleAdic() {
		return cantDisponibleAdic;
	}

	public void setCantDisponibleAdic(int cantDisponibleAdic) {
		this.cantDisponibleAdic = cantDisponibleAdic;
	}

	public int getUnidadesProdDescuento() {
		return unidadesProdDescuento;
	}

	public void setUnidadesProdDescuento(int unidadesProdDescuento) {
		this.unidadesProdDescuento = unidadesProdDescuento;
	}

	public int getDiasVigencia() {
		return diasVigencia;
	}

	public void setDiasVigencia(int diasVigencia) {
		this.diasVigencia = diasVigencia;
	}

	private int usuarioCreacion;
	private Date fechaCreacion;
	private int usuarioModificacion;
	private Date fechaModificacion;
	
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
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

	public int getDiasEnDescuento() {
		
		return diasEnDescuento;
	}
	
	public void setDiasEnDescuento(int diasEnDescuento) {
		this.diasEnDescuento = diasEnDescuento;
	}

}
