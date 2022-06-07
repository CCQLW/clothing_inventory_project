package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class aController {

    @RequestMapping("/a")
    public String a() {
        return "login.html";
    }

}
