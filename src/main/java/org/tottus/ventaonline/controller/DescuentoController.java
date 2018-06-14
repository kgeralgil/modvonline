package org.tottus.ventaonline.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tottus.ventaonline.service.DescuentoService;
import org.tottus.ventaonline.utils.Constantes;

@Controller
@RequestMapping("/descuentos")
public class DescuentoController {

	private static final String VIEW_SUFFIX = "descuento-";
	
	@Autowired
	private DescuentoService descuentoService;
	
	@RequestMapping("/")
	public String index() {
		return VIEW_SUFFIX + "principal";
	}
	
	@RequestMapping(value = "/generar", method = RequestMethod.POST)
	public String generarDescuento(RedirectAttributes redir, @RequestParam String dni) {
		try {
			Map<String, Object> resultado = descuentoService.generarDescuentosDiarios(dni);
			redir.addFlashAttribute("msg", resultado.get("mensaje"));
			redir.addFlashAttribute("descuentos", resultado.get("descuentos"));
		} catch (Exception e){
			redir.addFlashAttribute("msg", Constantes.ERR_GENERICO);
			e.printStackTrace();
		}
		return "redirect:/descuentos/";
	}
	
}
