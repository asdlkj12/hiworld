package com.example.demo.controller;

import com.example.demo.bean.UserBean;
import com.example.demo.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/user"})
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @RequestMapping(value = {"/loginPage"})
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = {"/registerPage"})
    public String registerPage(){
        return "register";
    }

    @PostMapping(value = {"/userLogin"})
    public String userLogin(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("role") String role){
        UserBean userbean = userLoginService.userLogin(name,password,role);
        //Distinguish between users of different roles
        if(userbean != null){
            if(role.equals("reviewer")) {
                userLoginService.setrewer(name);
                return "interface_reviewer";
            }
            if(role.equals("author")) {
                userLoginService.setName(name);
                return "enter_author";
            }
            if(role.equals("university")) {
                return "enter_university";
            }
        }
        return "error";
    }

    @RequestMapping(value = {"/userRegister"} )
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("password") String password,
                          @RequestParam("password2") String password2,
                          @RequestParam("role") String role){

        if(!password.equals(password2)){

            return "registration_false";
        }else {
            userLoginService.adduser(name,password,role);
            return"login";
            }
        }

    }
