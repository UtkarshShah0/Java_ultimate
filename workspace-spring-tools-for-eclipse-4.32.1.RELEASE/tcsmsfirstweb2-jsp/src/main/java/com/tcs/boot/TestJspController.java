package com.tcs.boot;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testjsp")
public class TestJspController {

	@GetMapping("/send")
	public String method(Model model) {

		ArrayList<Integer> salaries = new ArrayList<>(Arrays.asList(234822, 23321, 50000, 90000, 30000));
		model.addAttribute("pay", salaries);
		return "2";
	}

}
