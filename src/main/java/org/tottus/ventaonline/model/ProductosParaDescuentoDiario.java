package org.tottus.ventaonline.model;

public class ProductosParaDescuentoDiario {
	
	private int idProducto;
	private Double porcentajeVenta;
	private String descripcionProducto;
	private int stockActual;
	
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public ProductosParaDescuentoDiario() {
		
	}

	public Double getPorcentajeVenta() {
		return porcentajeVenta;
	}

	public void setPorcentajeVenta(Double porcentajeVenta) {
		this.porcentajeVenta = porcentajeVenta;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public int getStockActual() {
		return stockActual;
	}

	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

}
