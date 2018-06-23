package org.tottus.ventaonline.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tottus.ventaonline.model.Producto;
import org.tottus.ventaonline.model.sorter.ProdFVencimientoSorterStrategy;
import org.tottus.ventaonline.model.sorter.ProductoSorterContext;
import org.tottus.ventaonline.service.ProductoService;
import org.tottus.ventaonline.utils.Constantes;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	private static final String VIEW_SUFFIX = "producto-";

	@Autowired
	private ProductoService productoService;

	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		request.setAttribute("modo", "MODO_HOME");
		return VIEW_SUFFIX + "principal";
	}

	@RequestMapping("/consultar")
	public String consultarProductos(@RequestParam("textoBusqueda") String text,
			HttpServletRequest request, RedirectAttributes redir) {
		String returnPage = VIEW_SUFFIX + "principal";
		try {
			if(null == text || text.isEmpty()){
				redir.addFlashAttribute("msg", Constantes.ERR_NOMBRE_PROD_NULO);
				returnPage = "redirect:/productos/";
			}
			List<Producto> productos = productoService.filtrarProductosXNombre(text);
			request.setAttribute("productos", productos);
			if (productos.size() == 0) {
				request.setAttribute("modo", "MODO_VACIO");
			} else {
				request.setAttribute("modo", "MODO_CONSULTA");
			}
		} catch (Exception e){
			redir.addFlashAttribute("msg", Constantes.ERR_GENERICO);
			returnPage = "redirect:/productos/";
			e.printStackTrace();
		}
		return returnPage;
	}

	@RequestMapping("/agregar")
	public String consultarProductos(@RequestParam int idProducto,
			HttpServletRequest request, RedirectAttributes redir) {
		String returnPage = VIEW_SUFFIX + "principal";
		try {
			request.setAttribute("modo", "MODO_RECOMENDACION");
			List<Producto> productosRecomendados = productoService.buscarProductosRecomendadosXIdProducto(idProducto);

			// Ordenar Productos
			ProductoSorterContext sorterContext = new ProductoSorterContext();
			sorterContext.setSortStrategy(new ProdFVencimientoSorterStrategy());
			sorterContext.sortProducto(productosRecomendados);

			if(productosRecomendados.size() >= 4){
				productosRecomendados = productosRecomendados.subList(0, 4);
			}

			request.setAttribute("productoAgregado", productoService.buscarProdcuctoXId(idProducto));
			request.setAttribute("productosRecomendados", productosRecomendados);
			if (productosRecomendados.size() > 0) {
				request.setAttribute("recomendacion", "MODO_LISTA");
			}
		} catch (Exception e){
			redir.addFlashAttribute("msg", Constantes.ERR_GENERICO);
			returnPage = "redirect:/productos/";
			e.printStackTrace();
		}
		return returnPage;
	}

}
