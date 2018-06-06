package org.tottus.ventaonline.utils.validation;

import java.util.List;

import org.tottus.ventaonline.utils.Util;

public class Validator {

	public static String validate(List<ValidationContext> validations){
		String resultado = "";
		for(ValidationContext validation : validations){
			resultado = validateField(validation);
			if(!resultado.isEmpty()){
				break;
			}
		}
		return resultado;
	}
	
	private static String validateField(ValidationContext validationContext){
		boolean valido = true;
		switch(validationContext.getValidationType()){
			case NOT_NULL:
				valido = !Util.isNullOrEmpty(validationContext.getFieldValue());
				break;
			case EXACT_SIZE:
				valido = validationContext.getFieldValue().toString()
						.length() == validationContext.getFieldSize();
				break;
			case REGEX:
				valido = validationContext.getFieldValue().toString()
						.matches(validationContext.getRegex());
				break;
			default: valido = true;
		}
		String resultado = "";
		if(!valido){
			resultado = validationContext.getMessage() + "<br/>" 
					+ "Campo: " + validationContext.getFieldName() + "<br/>"
					+ "Valor Recibido: " + (Util.isNullOrEmpty(validationContext.getFieldValue()) ? 
							"":validationContext.getFieldValue().toString());
		}
		return resultado;
	}
	
}
