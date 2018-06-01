package org.tottus.ventaonline.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tottus.ventaonline.model.Producto;
import org.tottus.ventaonline.model.sorter.ProdFVencimientoSorterStrategy;
import org.tottus.ventaonline.model.sorter.ProductoSorterContext;
import org.tottus.ventaonline.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@GetMapping("/")
	public String home(HttpServletRequest request) {
		request.setAttribute("modo", "MODO_HOME");
		return "index";
	}

	@GetMapping("/consultar")
	public String consultarProductos(@RequestParam String codigoProducto, HttpServletRequest request) {

		List<Producto> productos = productoService.filtrarProductosXNombre(codigoProducto);
		request.setAttribute("productos", productos);
		if (productos.size() == 0) {
			request.setAttribute("modo", "MODO_VACIO");
		} else {
			request.setAttribute("modo", "MODO_CONSULTA");
		}

		return "index";
	}

	@GetMapping("/agregar")
	public String consultarProductos(@RequestParam int idProducto, HttpServletRequest request) {
		request.setAttribute("modo", "MODO_RECOMENDACION");
		List<Producto> productosRecomendados = productoService.buscarProductosRecomendadosXIdProducto(idProducto);
		
		// Ordenar Productos
		ProductoSorterContext sorterContext = new ProductoSorterContext();
		sorterContext.setSortStrategy(new ProdFVencimientoSorterStrategy());
		sorterContext.sortProducto(productosRecomendados);
		
		request.setAttribute("productoAgregado", productoService.buscarProdcuctoXId(idProducto));
		request.setAttribute("productosRecomendados", productosRecomendados);
		if (productosRecomendados.size() > 0) {
			request.setAttribute("recomendacion", "MODO_LISTA");
		} else {
			System.out.println("Prueba----->" + productosRecomendados.size());
		}

		return "index";
	}

}
