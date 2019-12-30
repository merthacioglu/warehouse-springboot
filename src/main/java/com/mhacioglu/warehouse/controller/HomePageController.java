package com.mhacioglu.warehouse.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mhacioglu.warehouse.domain.User;

@Controller
public class HomePageController {
	@RequestMapping(value = {"/", "/home"})
	public ModelAndView getHomePage(@AuthenticationPrincipal User user) {
        return new ModelAndView("homePage", "user", user); // giris yapmis olan kullanici bilgileri view'a aktarilir
    }
	
	
}
