package org.tottus.ventaonline.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tottus.ventaonline.model.ProductoDescuentoDiario;
import org.tottus.ventaonline.model.ProductosParaDescuentoDiario;
import org.tottus.ventaonline.service.AnalisisService;
import org.tottus.ventaonline.utils.Constantes;

@Controller
@RequestMapping("/analisis")
public class AnalisisController {

	private static final String VIEW_SUFFIX = "analisis-";
	private List<ProductoDescuentoDiario> productosEnDescuentoDiario;

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

		List<ProductosParaDescuentoDiario> productos = analisisService.ConsultarProductosParaDescuentoDiario(fechaIni,
				fechaFin);
		request.setAttribute("productosParaDescuento", productos);
		System.out.println("" + productos.size());
		if (productos.size() == 0) {
			request.setAttribute("CproductosParaDescuento", "MODO_VACIO");
		} else {
			request.setAttribute("CproductosParaDescuento", "MODO_CONSULTA");
		}

		productosEnDescuentoDiario = analisisService.ConsultarProductosEnDescuento(1);
		request.setAttribute("productosDescuentoDiario", productosEnDescuentoDiario);
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
			@RequestParam(name = "fechaIniVigencia") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaIniVigencia,
			@RequestParam(name = "fechaFinVigencia") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFinVigencia,
			@RequestParam(name = "restriccionCantidad") int restriccionCantidad, HttpServletRequest request) {

		ProductoDescuentoDiario productoDescuentoDiario = new ProductoDescuentoDiario();
		productoDescuentoDiario.setIdProducto(idProducto);
		productoDescuentoDiario.setPorcentajeDescuento(porcentajeDescuento);
		productoDescuentoDiario.setCantidadDisponible(cantidadDisponible);
		productoDescuentoDiario.setFechaIniVigencia(fechaIniVigencia);
		productoDescuentoDiario.setFechaFinVigencia(fechaFinVigencia);
		productoDescuentoDiario.setRestriccionCantidad(restriccionCantidad);

		analisisService.agregarDescuentoDiario(productoDescuentoDiario);
		// RN019: Sólo se puede ingresar un máximo de 10 productos a descuentos
		// diarios por ventas bajas.
		System.out.println("Cantidad de productos diarios registrados "+ productosEnDescuentoDiario.size());
		
		if (productosEnDescuentoDiario.size() == 10) {
			Map<String, Object> resultado = new HashMap<>();
			resultado.put("mensaje", Constantes.ERR_MAX10_PRODUCTOSDIARIOS);
			redir.addFlashAttribute("msg", resultado.get("mensaje"));
		}

		return "analisis-principal";
	}

}
