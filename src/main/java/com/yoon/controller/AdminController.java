package com.yoon.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@RequestMapping("/admin")
@Controller
public class AdminController {
	
	
	 private static final Logger logger = org.slf4j.LoggerFactory.getLogger(AdminController.class);	
	 
	
	 
	 
	@GetMapping("/main")
	public void adminMain() {
		
		
	}

}
