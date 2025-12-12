package com.tcs.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("")
public class RegisterationController {
	
	
	@Autowired
	UserRepository repository;
	
	
	
	@GetMapping("/signup")
	public String method() {
		return "registeration";
	}
	
	
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user, Model model) {
		
		if (user.getUserName().length() >= 8 && user.getPassword().length() >= 8) {
			
			System.out.println(user);
			User newUser = repository.save(user);
			model.addAttribute("uid", user.getUserId());
			model.addAttribute("uname", user.getUserName());
			model.addAttribute("password", user.getPassword());
//			model.addAttribute("uname", user.getUserName());
			return "success";
		
		} else {
			return "registeration";
		}
		
		
	}
	
	
	
	
//	@PostMapping("/register")
//	public String registerUser(@RequestParam String userName, @RequestParam String password) {
//		
//		if (userName.length() >= 8 && password.length() >= 8) {
//			return "success";
//		
//		} else {
//			return "registeration";
//		}
//		
//		
//	}
	
	
}
