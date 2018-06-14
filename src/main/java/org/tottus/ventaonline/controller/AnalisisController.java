package org.tottus.ventaonline.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tottus.ventaonline.model.ProductoDescuento;
import org.tottus.ventaonline.model.ProductosParaDescuentoDiario;
import org.tottus.ventaonline.service.AnalisisService;
import org.tottus.ventaonline.utils.Constantes;

@Controller
@Scope("session")
@RequestMapping("/analisis")
public class AnalisisController {

	private static final String VIEW_SUFFIX = "analisis-";
	private List<ProductoDescuento> productosEnDescuentoDiario;
	private List<ProductosParaDescuentoDiario> productos;

	@Autowired
	private AnalisisService analisisService;

	@RequestMapping("/")
	public String inicio() {
		return VIEW_SUFFIX + "principal";
	}

	@RequestMapping("/consultar")
	public String consultarProductosEnDescuento(
			@RequestParam(name = "fechaIni") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaIni,
			@RequestParam(name = "fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin,
			HttpServletRequest request) {

		System.out.println(fechaIni + "-" + fechaFin);

		productos = analisisService.ConsultarProductosParaDescuentoDiario(fechaIni,
				fechaFin);
		request.getSession().setAttribute("productosParaDescuento", productos);
		System.out.println("Productos para descuento " + productos.size());
		if (productos.size() == 0) {
			request.setAttribute("CproductosParaDescuento", "MODO_VACIO");
		} else {
			request.setAttribute("CproductosParaDescuento", "MODO_CONSULTA");
		}
		
		
		productosEnDescuentoDiario = analisisService.ConsultarProductosEnDescuento(1);
		System.out.println("Producto en descuento "+ productosEnDescuentoDiario.size());
		request.getSession().setAttribute("productosDescuentoDiario", productosEnDescuentoDiario);
		if (productosEnDescuentoDiario.size() == 0) {
			request.setAttribute("cproductosEnDescuento", "MODO_VACIO_PRODUCTOSDIARIO");
		} else {
			request.setAttribute("cproductosEnDescuento", "MODO_LISTA");
		}

		return "analisis-principal";
	}

	@RequestMapping(value = "/agregar-descuento", method = RequestMethod.POST)
	public String agregarDescuento(RedirectAttributes redir, @RequestParam(name = "idProducto") int idProducto,
			@RequestParam(name = "porcentajeDescuento") double porcentajeDescuento,
			@RequestParam(name = "cantidadDisponible") int cantidadDisponible,
			@RequestParam(name = "diasVigencia") int diasvigencia, HttpServletRequest request) {

		ProductoDescuento productoDescuento = new ProductoDescuento();
		productoDescuento.setIdProducto(idProducto);
		productoDescuento.setPctDescuento(porcentajeDescuento);
		productoDescuento.setCantDisponible(cantidadDisponible);
		productoDescuento.setDiasVigencia(diasvigencia);

		
		productos = (List<ProductosParaDescuentoDiario>) request.getSession().getAttribute("productosParaDescuento");
		productosEnDescuentoDiario = (List<ProductoDescuento>) request.getSession().getAttribute("productosDescuentoDiario");

		// RN019: Sólo se puede ingresar un máximo de 10 productos a descuentos, diarios por ventas bajas.
		if (productosEnDescuentoDiario!=null && productosEnDescuentoDiario.size() == 10) {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("mensaje", Constantes.ERR_MAX10_PRODUCTOSDIARIOS);
			redir.addFlashAttribute("msg", resultado.get("mensaje"));
		}else if(productos.get(1).getStockActual()<productoDescuento.getCantDisponible()){
			
			//Contemplar: Si el stock a agregar es 0 o mayor al stock actual del producto
			
		}
		else{
			analisisService.agregarDescuentoDiario(productoDescuento);
			System.out.println("Cantidad de productos diarios registrados "+ productosEnDescuentoDiario.size());
		}

		return "analisis-principal";
	}
	
	@RequestMapping(value = "/eliminar-descuento", method = RequestMethod.GET)
	public String eliminarDescuento(RedirectAttributes redir, @RequestParam(name = "idProducto") int idProducto) {

		
		System.out.println("Descuento Eliminado");
		
//		ProductoDescuento productoDescuento = new ProductoDescuento();
//		productoDescuento.setIdProducto(idProducto);
//
//		
//		if (productosEnDescuentoDiario.size() == 10) {
//			Map<String, Object> resultado = new HashMap<>();
//			resultado.put("mensaje", Constantes.ERR_MAX10_PRODUCTOSDIARIOS);
//			redir.addFlashAttribute("msg", resultado.get("mensaje"));
//		}else{
//			analisisService.agregarDescuentoDiario(productoDescuento);
//			// RN019: Sólo se puede ingresar un máximo de 10 productos a descuentos, diarios por ventas bajas.
//			System.out.println("Cantidad de productos diarios registrados "+ productosEnDescuentoDiario.size());
//		}

		return "analisis-principal";
	}

}
