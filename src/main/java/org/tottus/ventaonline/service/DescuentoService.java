package org.tottus.ventaonline.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tottus.ventaonline.dao.DescuentoRepository;
import org.tottus.ventaonline.utils.Constantes;
import org.tottus.ventaonline.utils.validation.ValidationContext;
import org.tottus.ventaonline.utils.validation.ValidationType;
import org.tottus.ventaonline.utils.validation.Validator;

@Service
@Transactional
public class DescuentoService {

	@Autowired
	private DescuentoRepository descuentoRepository;
	
	public Map<String, Object> generarDescuentosDiarios(String dni){
		Map<String, Object> resultado = new HashMap<>();
		// Validar DNI
		String resultadoVal = Validator.validate(validacionesDNI(dni));
		
		// Validar que sea su primera vez en el dia
		if(resultadoVal.isEmpty()){
			if(0 == descuentoRepository.validateExistingDNI(dni)){
				resultadoVal = Constantes.ERR_DNI_NO_REGISTRADO;
			} else if(0<descuentoRepository.validateDiscountAlreadyGenerated(dni)){
				resultadoVal = Constantes.ERR_DNI_YA_GENERO_DESCUENTOS;
			} else {
				// Generar Descuento Aleatorio
				resultado.put("descuentos", descuentoRepository.generarDescuentosDiarios(dni));
			}
		}
		resultado.put("mensaje", resultadoVal);
		return resultado;
	}
	
	private List<ValidationContext> validacionesDNI(String dni){
		List<ValidationContext> validaciones = new ArrayList<>();
		validaciones.add(
				new ValidationContext()
				.fieldName("DNI")
				.fieldValue(dni)
				.message(Constantes.ERR_CAMPO_NULO)
				.validationType(ValidationType.NOT_NULL));
		validaciones.add(
				new ValidationContext()
				.fieldName("DNI")
				.fieldValue(dni)
				.regex(Constantes.REGEX_SOLO_NUMEROS)
				.message(Constantes.ERR_SOLO_NUMEROS)
				.validationType(ValidationType.REGEX));
		validaciones.add(
				new ValidationContext()
				.fieldName("DNI")
				.fieldValue(dni)
				.fieldSize(8)
				.message(Constantes.ERR_CANTIDAD_INEXACTA)
				.validationType(ValidationType.EXACT_SIZE));
		return validaciones;
	}
	
}
