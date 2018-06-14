package org.tottus.ventaonline.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
	
	public static byte[] extractBytesFromFile(File imageFile) throws IOException {
		byte[] bytesArray = new byte[(int) imageFile.length()]; 

		  FileInputStream fis = new FileInputStream(imageFile);
		  fis.read(bytesArray); //read file into bytes[]
		  fis.close();
					
		  return bytesArray;
	}
	
}
