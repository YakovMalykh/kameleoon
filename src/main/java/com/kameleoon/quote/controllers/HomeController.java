package com.kameleoon.quote.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String getStr() {
        return "home";
    }
    @GetMapping("/auth")
    public String getAuth() {
        return "auth";
    }
}
