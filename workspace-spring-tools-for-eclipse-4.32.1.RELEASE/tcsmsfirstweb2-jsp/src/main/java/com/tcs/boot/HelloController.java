package com.tcs.boot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/test")
public class HelloController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String method(Model model) {
		
		model.addAttribute("tech", "springboot");
		model.addAttribute("name", "Alex");
		model.addAttribute("org", "Edureka");
		return "hello";
	}
}
