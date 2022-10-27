package com.nttdata.ws.prueba.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Angelica
 *
 */
@Controller
public class HomeController {
	
	@GetMapping(value = "/")
	public String index() {
		return "redirect:swagger-ui.html";
	}

}
