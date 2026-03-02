package com.example.BaiTap2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Xin chào Spring Boot 👋");
        return "index"; // trỏ tới index.html
    }
}


