package org.tottus.ventaonline.utils;

public class Constantes {

	// REGEX
	public static final String REGEX_SOLO_NUMEROS = "^[0-9]*$";
	
	// MENSAJES ERROR
	public static final String ERR_SOLO_NUMEROS = "El valor ingresado debe contener solo números.";
	public static final String ERR_CANTIDAD_INEXACTA = "Longitud inadecuada para el campo.";
	public static final String ERR_CAMPO_NULO = "No se ha ingresado ningún valor.";
	public static final String ERR_DNI_YA_GENERO_DESCUENTOS = "Usted ya generó sus descuentos del día, inténtelo nuevamente mañana";
	public static final String ERR_MAX10_PRODUCTOSDIARIOS = "Se tienen 10 productos configurados como máximo en descuento diario";
	public static final String ERR_PRODUCTO_DESCUENTOTRADICIONAL = "El producto elegido ya cuenta con un descuento activo";
	public static final String MSG_P = "No hay productos con niveles de venta inferiores al esperado";
	public static final String ERR_STOCK_INSUFICIENTE = "Stock insuficiente";
}
