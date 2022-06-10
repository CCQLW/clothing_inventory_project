package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller

public class aController {
    @ResponseBody
    @GetMapping("/a")
    public String a(HttpServletRequest request) {
//        String aaaaa = (String) request.getSession().getAttribute("aaaaa");
//        System.out.println("aaaaa:" + aaaaa);
        return "<html><body><h1>A</h1><div id='created'>CST 2022</div><div>asd</div></body></html>";
    }
}
