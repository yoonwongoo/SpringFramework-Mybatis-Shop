package com.yoon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

	
	@GetMapping("/")
	public String main() {
		
		return "main";
	} 
}
