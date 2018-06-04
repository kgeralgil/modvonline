package org.tottus.ventaonline.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		return "principal";
	}

}
