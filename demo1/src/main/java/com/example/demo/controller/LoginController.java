package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;


@Controller  //此处注意RestController和Controller的区别，此处能打开login.html，另一个则不能
public class LoginController {
    @Autowired
    //private JdbcTemplate jdbcTemplate;
    @RequestMapping("/login")
    public String show(){
        return "login";
    }

}
