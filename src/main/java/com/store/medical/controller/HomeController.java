package com.store.medical.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private String appMode;
	
	@Autowired
	public HomeController(Environment environment) {
		appMode = environment.getProperty("app-mode");
	}
	
	@RequestMapping(path = "/onkarMedical")
	public String home(Model model) {
		model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Amit P");
        model.addAttribute("mode", appMode);
		return "index";
	}
	
	@RequestMapping("")
	public String homeRedirect() {
		return "redirect:/onkarMedical";
	}
}
