package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


@Controller
public class LoginController {
    @Autowired

    @RequestMapping("/login")
    public String show(){
        return "login";
    }

}
