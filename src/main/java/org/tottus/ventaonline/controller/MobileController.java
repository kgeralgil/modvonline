package org.tottus.ventaonline.controller;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tottus.ventaonline.model.MobileResponse;
import org.tottus.ventaonline.model.Producto;
import org.tottus.ventaonline.service.ProductoService;

@RestController
@RequestMapping("/rest")
public class MobileController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/producto/{codigo}")
	public ResponseEntity<?> buscaProductoPorCodigo(@PathVariable("codigo") String codigo) throws Exception {

		try {
		
			MobileResponse mobileResponse = new MobileResponse();
			mobileResponse.setCode(100);
			mobileResponse.setMessage("Prueba de Webservice");
			
            Producto producto = productoService.buscarProductoXEtiqueta(codigo);

            if(producto == null)
                return ResponseEntity.notFound().build();
            
            mobileResponse.setData(producto);
            
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            
            
            return ResponseEntity.ok(mobileResponse);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getCause());
            throw e;
        }
		
	}
	
}
