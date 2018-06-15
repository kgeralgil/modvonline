package org.tottus.ventaonline.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/analisis")
public class AnalisisController {

	private static final String VIEW_SUFFIX = "analisis-";
	private List<ProductoDescuento> productosEnDescuentoDiario;
	private List<ProductosParaDescuentoDiario> productos;

	public List<ProductoDescuento> getProductosEnDescuentoDiario() {
		return productosEnDescuentoDiario;
	}

	public void setProductosEnDescuentoDiario(List<ProductoDescuento> productosEnDescuentoDiario) {
		this.productosEnDescuentoDiario = productosEnDescuentoDiario;
	}

	public List<ProductosParaDescuentoDiario> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductosParaDescuentoDiario> productos) {
		this.productos = productos;
	}

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
			HttpServletRequest request,RedirectAttributes redir) {
		
		try {
	
	

		System.out.println(fechaIni + "-" + fechaFin);

		if (fechaFin.before(fechaIni)) {
			request.setAttribute("msg", Constantes.ERR_FECHA_INCORRECTAS);
		} else {
			productos = analisisService.ConsultarProductosParaDescuentoDiario(fechaIni, fechaFin);

			request.getSession().setAttribute("productosParaDescuento", productos);
			System.out.println("Productos para descuento " + productos.size());
			if (productos.size() <= 1 && productos.get(0).getStockActual() == 0) {
				request.setAttribute("CproductosParaDescuento", "MODO_VACIO");
			} else {
				request.setAttribute("CproductosParaDescuento", "MODO_CONSULTA");
			}

			productosEnDescuentoDiario = analisisService.ConsultarProductosEnDescuento(1);

			request.getSession().setAttribute("productosDescuentoDiario", productosEnDescuentoDiario);

			System.out.println("Producto en descuento " + productosEnDescuentoDiario.size());

			if (productosEnDescuentoDiario.size() == 0) {
				request.setAttribute("cproductosEnDescuento", "MODO_VACIO_PRODUCTOSDIARIO");
			} else {
				request.setAttribute("cproductosEnDescuento", "MODO_LISTA");
			}
		}
		} catch (Exception e) {
			
			redir.addFlashAttribute("msg", Constantes.ERR_GENERICO);
			e.printStackTrace();
		}
		return "analisis-principal";
	}

	@RequestMapping(value = "/agregar-descuento", method = RequestMethod.GET)
	public String agregarDescuento(RedirectAttributes redir, @RequestParam(name = "idProducto") int idProducto,
			@RequestParam(name = "porcentajeDescuento") double porcentajeDescuento,
			@RequestParam(name = "cantidadDisponible") int cantidadDisponible,
			@RequestParam(name = "diasVigencia") int diasvigencia, HttpServletRequest request) {

		if (analisisService.consultarProductosParaDescuentoDiario(idProducto) == 0) {

			System.out.println("Productos para descuento * " + productos.size());
			System.out.println("Producto en descuento * " + productosEnDescuentoDiario.size());
			boolean errorStock = false;

			ProductoDescuento productoDescuento = new ProductoDescuento();
			productoDescuento.setIdProducto(idProducto);
			productoDescuento.setPctDescuento(porcentajeDescuento);
			productoDescuento.setCantDisponible(cantidadDisponible);
			productoDescuento.setDiasVigencia(diasvigencia);

			// Contemplar: Si el stock a agregar es mayor al stock actual del
			// producto
			for (int i = 0; i < productos.size(); i++) {
				if (productos.get(i).getIdProducto() == productoDescuento.getIdProducto()) {
					if (0.3*productos.get(i).getStockActual() < productoDescuento.getCantDisponible()) {
						errorStock = true;
						request.setAttribute("msg", Constantes.ERR_STOCK_INSUFICIENTE);
						System.out.println("stock insuficiente  ****************");

					}
					break;
				}
			}
			System.out.println("Dias Vigencias  ****************"+productoDescuento.getDiasVigencia());
			if (errorStock == false) {

				// RN019: Sólo se puede ingresar un máximo de 10 productos a
				// descuentos,
				// diarios por ventas bajas.
				if (productosEnDescuentoDiario.size() == 10) {
					request.setAttribute("msg", Constantes.ERR_MAX10_PRODUCTOSDIARIOS);
					System.out.println("Prodcutos con descuento Diario son 10 ****************");

				}else if (productoDescuento.getDiasVigencia()<3||productoDescuento.getDiasVigencia()>15) {
					request.setAttribute("msg", Constantes.ERR_DIAS_VIGENCIAS_FUERARANGO);
					System.out.println("Dias Vigencias  ****************"+productoDescuento.getDiasVigencia());
					
				}else if (productoDescuento.getPctDescuento()==0||productoDescuento.getPctDescuento()>=100) {
					request.setAttribute("msg", Constantes.ERR_PORCENTAJE_INVALIDO);
					System.out.println("Porcentaje Invalido ****************"+productoDescuento.getPctDescuento());
					
				} else {
					analisisService.agregarDescuentoDiario(productoDescuento);
					request.setAttribute("msg", Constantes.MSG_PRODUCTO_ACTUALIZADO);
					productosEnDescuentoDiario.add(productoDescuento);
					
					//-----------------------------
					productosEnDescuentoDiario = analisisService.ConsultarProductosEnDescuento(1);

					request.getSession().setAttribute("productosDescuentoDiario", productosEnDescuentoDiario);

					System.out
							.println("Cantidad de productos diarios registrados " + productosEnDescuentoDiario.size());
				}
			}
		} else {
			
			request.setAttribute("msg", Constantes.ERR_PRODUCTO_TIENE_DESCUENTO);

		}

		if (productos.size() == 0) {
			request.setAttribute("CproductosParaDescuento", "MODO_VACIO");
		} else {
			request.setAttribute("CproductosParaDescuento", "MODO_CONSULTA");
		}

		if (productosEnDescuentoDiario.size() == 0) {
			request.setAttribute("cproductosEnDescuento", "MODO_VACIO_PRODUCTOSDIARIO");
		} else {
			request.setAttribute("cproductosEnDescuento", "MODO_LISTA");
		}

		return "analisis-principal";
	}

	@RequestMapping(value = "/eliminar-descuento", method = RequestMethod.GET)
	public String eliminarDescuento(RedirectAttributes redir, @RequestParam(name = "idProducto") int idProducto,
			HttpServletRequest request) {

		System.out.println("Descuento Eliminado");
		analisisService.eliminarDescuentoDiario(idProducto);

		for (int i = 0; i < productosEnDescuentoDiario.size(); i++) {
			if (productosEnDescuentoDiario.get(i).getIdProducto() == idProducto) {
				productosEnDescuentoDiario.remove(i);
			}
		}

		request.getSession().setAttribute("productosDescuentoDiario", productosEnDescuentoDiario);
		request.setAttribute("msg", Constantes.MSG_PRODUCTO_ACTUALIZADO);

		if (productos.size() == 0) {
			request.setAttribute("CproductosParaDescuento", "MODO_VACIO");
		} else {
			request.setAttribute("CproductosParaDescuento", "MODO_CONSULTA");
		}

		if (productosEnDescuentoDiario.size() == 0) {
			request.setAttribute("cproductosEnDescuento", "MODO_VACIO_PRODUCTOSDIARIO");
		} else {
			request.setAttribute("cproductosEnDescuento", "MODO_LISTA");
		}

		return "analisis-principal";
	}

}
