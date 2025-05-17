package com.example.l5_20211049.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/juego";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}