package org.tottus.ventaonline.utils;

public class Util {

	public static boolean isNullOrEmpty(Object value){
		boolean resultado = value==null;
		if(!resultado){
			resultado = value.toString().length() == 0;
		}
		return resultado;
	}
	
	public static void main(String[] args){
//		System.out.println(isNullOrEmpty(2.5f));
	}
	
}
