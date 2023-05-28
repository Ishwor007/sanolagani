package com.bitflip.sanolagani.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bitflip.sanolagani.models.User;

@Controller
public class RegisterController {
	@GetMapping("/register")
	public String registerPage(@ModelAttribute("user")User user) {
		return "signup";
	}
}
