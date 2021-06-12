package com.store.medical.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

	@GetMapping("searchProduct")
	public String searchProducts() {
		return "search-product";
	}
	
	
}
