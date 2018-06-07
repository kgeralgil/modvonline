package org.tottus.ventaonline.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tottus.ventaonline.model.MobileResponse;
import org.tottus.ventaonline.model.Producto;

@RestController
@RequestMapping("/rest")
public class MobileController {

	@RequestMapping("/producto/{codigo}")
	public MobileResponse buscaProductoPorCodigo(@PathVariable("codigo") String codigo) {
		MobileResponse mobileResponse = new MobileResponse();
		mobileResponse.setCode(100);
		mobileResponse.setMessage("PRUEBA");
		Producto producto = new Producto();
		producto.setCodigoProducto(codigo);
		producto.setDescripcion("PRUEBA");
		mobileResponse.setData(producto);
		return mobileResponse;
	}
	
}
