package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String hello(){
        return "forward:/login.html";
    }

    @RequestMapping("/index")
    public String index(){return "forward:/html/home.html";}
}
