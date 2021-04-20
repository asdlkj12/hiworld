package com.example.demo.controller;

import com.example.demo.bean.UserBean;
import com.example.demo.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/user"})
public class UserLoginController {

    /**
     * 注入service
     */
    @Autowired
    private UserLoginService userLoginService;

    /**
     * 跳转到用户登录页面
     * @return 登录页面
     */
    @RequestMapping(value = {"/loginPage"})
    public String loginPage(){
        return "login";
    }

    /**
     * 跳转到用户注册页面
     * @return 注册页面
     */
    @RequestMapping(value = {"/registerPage"})
    public String registerPage(){
        return "register";
    }

    /**
     * 获取用户名与密码，用户登录
     * @return 登录成功页面
     */
    @PostMapping(value = {"/userLogin"})
    public String userLogin(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("role") String role){
        UserBean userbean = userLoginService.userLogin(name,password,role);

        if(userbean != null){
            //登录成功//将用户信息放入session
            if(role.equals("reviewer")) {
                return "interface_student";
            }
            if(role.equals("author")) {
                return "enter_author";
            }
            if(role.equals("university")) {
                return "enter_university";
            }
        }
        return "error";
    }

    /**
     * 注册新用户
     * @return 注册结果
     */
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
