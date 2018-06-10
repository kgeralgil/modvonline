package org.tottus.ventaonline.utils;

public class Util {

	public static boolean isNullOrEmpty(Object value){
		boolean resultado = value==null;
		if(!resultado){
			resultado = value.toString().length() == 0;
		}
		return resultado;
	}
	
	public static String objectToString(Object string){
		return (string == null) ? "" : string.toString();
	}
	
}
