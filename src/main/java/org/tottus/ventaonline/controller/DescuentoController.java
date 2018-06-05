package org.tottus.ventaonline.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/descuentos")
public class DescuentoController {

	private static final String VIEW_SUFFIX = "descuento-";
	
	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		return VIEW_SUFFIX + "principal";
	}
	
}
