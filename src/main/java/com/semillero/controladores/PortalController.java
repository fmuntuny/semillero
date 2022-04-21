package com.semillero.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalController {
	
	@GetMapping("/")
	public String bienvenida() {
		return "index.html";
	}
}
