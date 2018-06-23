package org.tottus.ventaonline.utils;

public class Constantes {

	// REGEX
	public static final String REGEX_SOLO_NUMEROS = "^[0-9]*$";

	// MENSAJES ERROR
	public static final String ERR_SOLO_NUMEROS = "El valor ingresado debe contener solo números.";
	public static final String ERR_CANTIDAD_INEXACTA = "Longitud inadecuada para el campo.";
	public static final String ERR_CAMPO_NULO = "No se ha ingresado ningún valor.";
	public static final String ERR_DNI_YA_GENERO_DESCUENTOS = "Usted ya generó sus descuentos del día, inténtelo nuevamente mañana.";
	public static final String ERR_DNI_NO_REGISTRADO = "El DNI ingresado no se encuentra registrado. Por favor regístrese e inténtelo nuevamente.";
	public static final String ERR_MAX10_PRODUCTOSDIARIOS = "Se tienen 10 productos configurados como máximo en descuento diario";
	public static final String ERR_PRODUCTO_DESCUENTOTRADICIONAL = "El producto elegido ya cuenta con un descuento activo";
	public static final String ERR_STOCK_INSUFICIENTE = "Las unidades a agregar a Descuento Diario no debe exceder el 30% del Stock Actual";
	public static final String ERR_NOMBRE_PROD_NULO = "Para brindarte un mejor servicio, te invitamos a que ingreses el nombre del producto.";
	public static final String ERR_GENERICO = "Estamos trabajando para brindarle un mejor servicio. Te invitamos a que visites nuestra sección de Productos http://www.tottus.com.pe/";
	public static final String MSG_PRODUCTO_ACTUALIZADO = "Se actualizó la información correctamente";
	public static final String ERR_PRODUCTO_TIENE_DESCUENTO = "El producto elegido ya cuenta con un descuento activo";
	public static final String ERR_FECHA_INCORRECTAS = "Fecha Inicial no puede ser mayor a la fecha Final";
	public static final String ERR_DIAS_VIGENCIAS_FUERARANGO = "Días de vigencias puede tener valores entre 3 y 15";
	public static final String ERR_PORCENTAJE_INVALIDO = "Porcentaje de Descuento inválido";

}
