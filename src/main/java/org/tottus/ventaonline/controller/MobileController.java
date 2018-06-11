package org.tottus.ventaonline.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tottus.ventaonline.model.Descuento;
import org.tottus.ventaonline.model.MobileRequest;
import org.tottus.ventaonline.model.MobileResponse;
import org.tottus.ventaonline.model.Producto;
import org.tottus.ventaonline.service.MobileService;
import org.tottus.ventaonline.utils.Util;

@RestController
@RequestMapping("/rest")
public class MobileController {

	@Autowired
	private MobileService mobileService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/descuento/{codigo}", method=RequestMethod.POST)
	public ResponseEntity<?> buscaProductoPorCodigo(@PathVariable("codigo") String codigo,
			@RequestBody MobileRequest request) throws Exception {
		try {
            Map<String, Object> map = mobileService.searchQRDiscount(codigo, request.getIdmovil());
            MobileResponse mobileResponse = new MobileResponse();
            mobileResponse.setCode(Integer.valueOf(Util.objectToString(map.get("codResultado"))));
            mobileResponse.setMessage(Util.objectToString(map.get("msgResultado")));
            List<Descuento> descuentos = (List<Descuento>) map.get("descuento");
            if(descuentos.size() > 0){
            	mobileResponse.setData(descuentos.get(0));
            }
            return ResponseEntity.ok(mobileResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getCause());
            throw e;
        }
	}
	
	@RequestMapping(value="/producto/texto/", method = RequestMethod.POST)
	public ResponseEntity<?> searchByVoice(@RequestBody MobileRequest request) throws Exception {
		try {
            List<Producto> productos = mobileService.searchProductsByVoice(request.getTextPhrase());
            MobileResponse mobileResponse = new MobileResponse();
            mobileResponse.setCode(productos.size()>0?1:0);
            mobileResponse.setMessage(productos.size()>0?"Correcto":"No se encontraron productos");
            mobileResponse.setProductos(productos);
            return ResponseEntity.ok(mobileResponse);
        } catch (Exception e) {
            System.out.println("Error: " + e.getCause());
            throw e;
        }
	}
	
}
