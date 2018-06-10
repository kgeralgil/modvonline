package org.tottus.ventaonline.controller;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tottus.ventaonline.model.ControlPromocion;
import org.tottus.ventaonline.model.MobileResponse;
import org.tottus.ventaonline.model.Producto;
import org.tottus.ventaonline.service.ProductoService;

@RestController
@RequestMapping("/rest")
public class MobileController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/producto/{codigo}/movil/{idmovil}")
	public ResponseEntity<?> buscaProductoPorCodigo(@PathVariable("codigo") String codigo, 
			@PathVariable("idmovil") String idmovil) throws Exception {

		try {
			MobileResponse mobileResponse = new MobileResponse();
			mobileResponse.setCode(100);
			mobileResponse.setMessage("Prueba de Webservice");
			
			ControlPromocion cp = new ControlPromocion();
			cp.setIdEquipoMovil(idmovil);
			cp.setCodigoProducto(codigo);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			cp.setFechaRegistro(dateFormat.format(date));
			cp.setEstado("A");
			
			productoService.guardarControlDescuentoBusquedaQR(cp);
			
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
